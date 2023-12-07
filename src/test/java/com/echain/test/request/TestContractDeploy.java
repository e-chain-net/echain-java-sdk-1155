package com.echain.test.request;

import com.echain.account.Account;
import com.echain.util.Util;
import org.json.JSONObject;

public class TestContractDeploy {
    public static Account owner = new Account("0xdb222aaaefb28a8a2b1533c6b098b819a80665b6","500a9faf63d80563207702295b9bf9f5dc98956fd253d81b8ba062f69b5cf6b1");
    static{
        HttpRequest.setServerCrtPath("/Users/yp-10221/Documents/e-chain.net.cn_server.crt");
    }

    public static void main(String args[]){
        try{
            String contractAddress = requestDeployContract("Deploy-HC-1",owner.getAddress());
            System.out.println("部署合约成功，合约地址：" + contractAddress);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String requestDeployContract(String reqNo,String owner) throws Exception{
        String payload = Util.formatDeployPayload(reqNo,owner);
        String response = HttpRequest.sendPost(Define.UrlDeploy,payload);
        JSONObject obj = new JSONObject(response);
        if(!obj.getString("code").equals("EC000000")){
            throw new Exception("请求合约部署失败："+obj.getString("message"));
        }else{
             return obj.getJSONObject("data").getString("contractAddress");
        }
    }
}
