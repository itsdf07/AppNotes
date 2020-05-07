package com.itsdf07.module.user.login;


import org.json.JSONException;
import org.json.JSONObject;


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/3/27
 */
public class LoginModel implements LoginContracts.ILoginModel {
    @Override
    public void login(String loginId, String pwd, final IModelCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("loginId", loginId);
            body.put("appOs", 1);
            body.put("version", "1.0.0");
            body.put("passwd", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        HttpUtils.getInstance().postRaw("api/usr/login", body.toString(), new HttpCallbackImpl<BaseBean>() {
//            @Override
//            public void onSuccess(BaseBean result) {
//                if (null != callback) {
//                    callback.onComplete(true);
//                }
//            }
//        }, null, null);

    }
}
