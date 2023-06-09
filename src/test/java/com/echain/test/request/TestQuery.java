package com.echain.test.request;

import com.echain.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestQuery {

    static{
        HttpRequest.setServerCrtPath("D:\\yeepay\\e-chain.net.cn_server.crt");
    }

    public static void main(String args[]) throws InterruptedException {
        //请求区块号
        TestGetBlockNumber();

        //请求交易收据，判断交易结果
        TestGetTransactionReceipt();

        //查询nft余额
        TestBalanceOf();
    }



    public static void TestGetBlockNumber(){
        String payload = Util.formatQueryPayload("getBlockNumber",new ArrayList<>());
        String response = HttpRequest.sendPost(Define.UrlQuery,payload);
        System.out.println("请求区块号：");
        try{
            int blockNumber = getBlockNumber();
            System.out.println("BlockNumber=" + blockNumber);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static int getBlockNumber() throws Exception {
        String payload = Util.formatQueryPayload("getBlockNumber",new ArrayList<>());
        String response = HttpRequest.sendPost(Define.UrlQuery,payload);
//        System.out.println(response);
        JSONObject obj = new JSONObject(response);
        if(!obj.getString("code").equals("EC000000")){
            throw new Exception("请求区块号失败："+obj.getString("message"));
        }else{
            return obj.getJSONObject("data").getInt("blockNumber");
        }
    }

    public static void TestGetTransactionReceipt(){
        System.out.println("");
        String txHash = "0x3ac02bbaca5e7e0adc05d0e36954c86ee39108d543542a49eed7420d445d2536";

        String payload = Util.formatQueryPayload("getTransactionReceipt",new ArrayList<>(Arrays.asList(txHash,false)));
//        System.out.println(payload.toString());

        String response = HttpRequest.sendPost(Define.UrlQuery,payload);
        System.out.println("请求交易收据：");
        System.out.println(response);
        JSONObject obj = new JSONObject(response);
        if(!obj.getString("code").equals("EC000000")){
            System.out.println("请求交易收据失败："+obj.getString("message"));
        }else{
            boolean ok = obj.getJSONObject("data").getJSONObject("result").getBoolean("statusOK");
            System.out.println("交易结果=" + (ok?"成功":"失败"));
        }
    }


    public static void TestBalanceOf(){
        System.out.println("");
        String account = "0x95a1a99be965777d8b0e42fe5ec1c161f6c3a5da";
        BigInteger tokenId = BigInteger.valueOf(1004);

        System.out.println("请求NFT余额：");
        try{
            int balance = getBalanceOf(account,tokenId,Define.ContractAddress);
            System.out.println("NFT余额="+balance);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String encodeBalanceOf(String address,BigInteger tokenId){
        Function function = new Function("balanceOf",
                Arrays.<Type>asList(new Address(address),
                        new Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return FunctionEncoder.encode(function);
    }

    private static int getBalanceOf(String account,BigInteger tokenId,String contractAddress) throws Exception {
        String input = encodeBalanceOf(account,tokenId);
        String payload = Util.formatQueryPayload("call",new ArrayList<>(Arrays.asList(contractAddress,input)));
//        System.out.println(payload.toString());
        String response = HttpRequest.sendPost(Define.UrlQuery,payload);
//        System.out.println(response);
        JSONObject obj = new JSONObject(response);
        if(!obj.getString("code").equals("EC000000")){
            throw new Exception("请求balanaceOf："+obj.getString("message"));
        }else{
            int status = obj.getJSONObject("data").getJSONObject("result").getInt("status");
            if(status != 0){
                throw new Exception("请求balanaceOf 失败，status="+status);
            }else{
                String output = obj.getJSONObject("data").getJSONObject("result").getString("output");
                BigInteger balance = new BigInteger(output.substring(2),16);
                return balance.intValue();
            }
        }
    }
}
