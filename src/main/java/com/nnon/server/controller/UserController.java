package com.nnon.server.controller;

import com.github.pagehelper.PageInfo;
import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.*;
import com.nnon.server.service.IEducationHistoryService;
import com.nnon.server.service.IUserService;
import com.nnon.server.service.IWorkHistoryService;
import com.nnon.server.service.impl.EducationHistoryImpl;
import com.nnon.server.service.impl.WorkHistoryServiceImpl;
import com.nnon.server.utils.*;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    TokenUtil utils;
    @Autowired
    IUserService userService;
    @Autowired
    IWorkHistoryService workHistoryService;
    @Autowired
    IEducationHistoryService educationService;

    @RequestMapping("/login")
    public CommonResult login(User user) {
        System.out.println(user.toString());
        String token = null;
        //匹配账号密码
        User result = userService.login(user);
        if (result != null) {
            //账号密码匹配成功 获取Token
            result.setLastLoginTime(new Date());
            //修改最近登录时间
            User updateUser = new User();
            updateUser.setLastLoginTime(result.getLastLoginTime());
            updateUser.setUid(result.getUid());

            userService.updateUser(updateUser);
            token = utils.sign(result.getUid(), "user");
        }
        if (token != null) {
            result.setRole(1);
            result.setToken(token);
            return CommonResult.success(result);
        }
        return CommonResult.unauthorized("操作失败");
    }

    @RequestMapping("/verify")
    public CommonResult verify() {
        return CommonResult.success("token一致");
    }

    @RequestMapping("/logout")
    public CommonResult logout(HttpServletRequest request) {
        Object verifyUid = request.getAttribute("verifyUid");
        RedisUtil redisUtil = utils.getRedisUtil();
        redisUtil.del(verifyUid.toString());
        System.out.println("推出登录成功");
        return CommonResult.success("推出登录成功");
    }

    @RequestMapping("/register")
    public CommonResult register(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
//        if(user.getCheckCode().equals(checkCode)){
        //判断账号是否重复
        User result = userService.findUserByUsername(user.getUsername());
        if(user.getCheckCode().equals(checkCode)){
            if (result == null) {
                user.setCreateTime(new Date());
                user.setModifyTime(new Date());
                user.setStatus(0);
                //开始创建账号
                Integer rs = userService.register(user);
                if (rs != 0) {
                    session.removeAttribute("checkCode");
                    return CommonResult.success("注册成功");
                } else {
                    return CommonResult.success("注册失败，请联系管理员处理");
                }
            } else {
                return CommonResult.success("账号已存在");
            }
        }else{
            return CommonResult.success("验证码错误");
        }

//        }else{
//            return CommonResult.success("验证码不正确");
//        }
    }

    @RequestMapping("/updateUser")
    public CommonResult updateUser(@RequestBody User user) {
        if (user.getUid() != null) {
            if (user.getAddress() != null) {
                StringBuilder addressStr = new StringBuilder();
                for (String address : user.getAddress()) {
                    addressStr.append(address);
                }
                String s = addressStr.toString();
                String substring = s.substring(0, s.length() - 1);
                user.setAddressStr(substring);
            }
            System.out.println(user);
            userService.updateUser(user);
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed("修改失败");
    }


    @RequestMapping("/getUser")
    public CommonResult getUser(HttpServletRequest request) {
        Object verifyUid = request.getAttribute("verifyUid");
        int uid = Integer.parseInt(verifyUid.toString());
        User user = userService.findUserByUid(uid);
        //获取工作经历
        user.setWorkHistoryList(workHistoryService.findWorkHistoryByUid(uid));
        //获取教育经历
        user.setEducationHistoryList(educationService.findEducationHistoryByUid(uid));
        return CommonResult.success(user);
    }

    @RequestMapping("findUser")
    public CommonResult findUser(@RequestBody User user) {
        System.out.println(user);
        ArrayList<User> userList = userService.findUser(user);
        return CommonResult.success(userList);
    }

    @RequestMapping("findUserPage")
    public CommonResult findUserPage(@RequestBody Pager<User> pager) throws ParseException {
        System.out.println(pager);
        PageInfo<User> pageInfo = userService.findUserPage(pager.getPage(), pager.getSize(), pager.getQueryParams());
        pager.setRows(pageInfo.getList());
        pager.setTotal(pageInfo.getTotal());
        pager.setHasNextPage(pageInfo.isHasNextPage());
        for (User user : pager.getRows()) {
            if (user.getCreateTime() != null) {
                user.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(user.getCreateTime()));
            }
            if (user.getModifyTime() != null) {
                user.setModifyTimeStr(DateTimeUtils.dateConvAppointStr(user.getModifyTime()));
            }
            if(user.getLastLoginTime()!=null){
                user.setLastLoginTimeStr(DateTimeUtils.dateConvAppointStr(user.getLastLoginTime()));
            }
        }
        return CommonResult.success(pager);
    }
    @RequestMapping("findUserByFuzzyPage")
    public CommonResult findUserByFuzzyPage(@RequestBody Pager<User> pager) throws ParseException {
        PageInfo<User> pageInfo = userService.findUserByFuzzyPage(pager.getPage(), pager.getSize(), pager.getQueryParams());
        pager.setRows(pageInfo.getList());
        pager.setTotal(pageInfo.getTotal());
        pager.setHasNextPage(pageInfo.isHasNextPage());
        for (User user : pager.getRows()) {
            if (user.getCreateTime() != null) {
                user.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(user.getCreateTime()));
            }
            if (user.getModifyTime() != null) {
                user.setModifyTimeStr(DateTimeUtils.dateConvAppointStr(user.getModifyTime()));
            }
            if(user.getLastLoginTime()!=null){
                user.setLastLoginTimeStr(DateTimeUtils.dateConvAppointStr(user.getLastLoginTime()));
            }
        }
        return CommonResult.success(pager);
    }

    @RequestMapping("findUserClassify")
    public CommonResult findUserClassify() {
        //用户封禁数量
        int banCount = userService.findUserBanCount();
        //活跃用户
        int loginCount = userService.findUserLoginCount();
        //用户总数
        int userCount = userService.findUserCount();

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("value", banCount);
        map.put("name", "封禁");
        list.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("value", loginCount);
        map1.put("name", "活跃");
        list.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("value", userCount - banCount - loginCount);
        map2.put("name", "不活跃");
        list.add(map2);
        return CommonResult.success(list);
    }

    @RequestMapping("getUserCount")
    public CommonResult getUserCount() {
        int userCount = userService.findUserCount();
        return CommonResult.success(userCount);
    }

    @RequestMapping("getLoginCount")
    public CommonResult getLoginCount() {
        int loginCount = userService.findUserLoginCount();
        return CommonResult.success(loginCount);
    }

    @RequestMapping("getOnlineCount")
    public CommonResult getOnlineCount() {
        int onlineCount = WebSocketServer.map.size();
        return CommonResult.success(onlineCount);
    }

    @RequestMapping("findUserSevenDaysCount")
    public CommonResult findUserSevenDaysCount() {
        List<Map<String, Object>> list = userService.findArticleSevenDaysCount();
        //获取某一天日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前日期
        Date date = new Date();
        //获取当前日期的前七天日期
        ArrayList<Integer> dateList = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            String date1 = DateTimeUtils.getDateBefore(date, i);
            Integer flag = 0;
            for (Map<String, Object> map : list) {
                if (map.get("days").equals(date1)) {
                    flag = ((Long) map.get("count")).intValue();
                }
            }
            dateList.add(flag);
        }
        return CommonResult.success(dateList);
    }
    @RequestMapping("sendEmailCheckCode")
    public CommonResult sendEmailCheckCode(@RequestBody User user, HttpServletRequest request) {

        String checkCode = "";
        for (int i = 0; i < 6; i++) {
            checkCode += (int) (Math.random() * 10);
        }
        String subject = "欢迎注册";
        String content = "您的验证码为：" + checkCode;
        EmailTest1 email = new EmailTest1();
        email.setAddress("601084853@qq.com",user.getEmail());
        email.send(subject, content);
        request.getSession().setAttribute("checkCode", checkCode);
        return CommonResult.success(checkCode);
    }
}