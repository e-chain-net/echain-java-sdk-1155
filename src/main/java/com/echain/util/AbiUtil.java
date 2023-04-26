package com.echain.util;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class AbiUtil {
    public static String encodeMint(String toAddress, BigInteger tokenId,BigInteger amount){
        Address to = new Address(toAddress);
        Uint256 tokenId256 = new Uint256(tokenId);
        Uint256 amount256 = new Uint256(amount);
        DynamicBytes data = new DynamicBytes(new byte[]{});
        Function function = new Function(
                "mint",                      // Function name
                Arrays.asList(to,tokenId256,amount256,data),         // Input parameters
                Collections.emptyList());  // Output parameter(s)

        return FunctionEncoder.encode(function);
    }

    public static String encodeTransferFrom(String from,String to,BigInteger tokenId,BigInteger amount){
        Address fromA = new Address(from);
        Address toA = new Address(to);
        Uint256 tokenId256 = new Uint256(tokenId);
        Uint256 amount256 = new Uint256(amount);
        DynamicBytes data = new DynamicBytes(new byte[]{});
        Function function = new Function(
                "safeTransferFrom",                      // Function name
                Arrays.asList(fromA,toA,tokenId256,amount256,data),         // Input parameters
                Collections.emptyList());  // Output parameter(s)

        return FunctionEncoder.encode(function);
    }

    public static String encodeBurn(String from,BigInteger tokenId,BigInteger amount){
        Address fromA = new Address(from);
        Uint256 tokenId256 = new Uint256(tokenId);
        Uint256 amount256 = new Uint256(amount);
        Function function = new Function(
                "burn",                      // Function name
                Arrays.asList(fromA,tokenId256,amount256),         // Input parameters
                Collections.emptyList());  // Output parameter(s)

        return FunctionEncoder.encode(function);
    }
}
