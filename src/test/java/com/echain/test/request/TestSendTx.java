package com.echain.test.request;

import com.echain.util.Util;
import org.json.JSONObject;

public class TestSendTx {
    static{
        HttpRequest.setServerCrtPath("D:\\yeepay\\e-chain.net.cn_server.crt");
    }

    public static void main(String args[]){
        //txHash,txSigned通过签名接口返回，参考TestSign
        String txHash = "0x77be6b8a0e3833a98058537da38756ed854c858bf0b82b17e77fb026b6905f9f";
        String txSigned = "0x1a1c2606636861696e30360667726f7570304103ad564d3630373630343139343137363630323738343532323136353339323430303034343937343432373235313336323633383635393737323037333430363833383736373433303033333532353134662a3078646364656364323238663539613233343238376665636536386164386662393466303136623132347d000100a4731133e900000000000000000000000095a1a99be965777d8b0e42fe5ec1c161f6c3a5da00000000000000000000000000000000000000000000000000000000000003e90000000000000000000000000000000000000000000000000000000000000002000000000000000000000000000000000000000000000000000000000000008000000000000000000000000000000000000000000000000000000000000000000b2d00002077be6b8a0e3833a98058537da38756ed854c858bf0b82b17e77fb026b6905f9f3d00004181bd6dbef10297c0e682c07c802fd635eeba26c9a6033c380bf484b54b13f39f748ee5531d849210a000b10eca0291d6701b5755370883041366646d273236df015001";
        try{
            sendTx(txHash,txSigned,"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void sendTx(String txHash,String txSigned,String callbackUrl) throws Exception{
        String payload = Util.formatSendTxPayload(txHash,txSigned,"");
//        System.out.println(payload);
        String response = HttpRequest.sendPost(Define.UrlSendTx,payload);
        System.out.println(response);
        JSONObject obj = new JSONObject(response);
        if(!obj.getString("code").equals("EC000001")){
            throw new Exception("请求发送交易失败："+obj.getString("message"));
        }
    }
}
