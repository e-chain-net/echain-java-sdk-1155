# EChain-Java-SDK-1155
适配标准``ERC1155``合约的 ``EChain-Server`` 签名SDK，提供标准``ERC1155``合约中如下方法的签名方法：
```
function mint(address account, uint256 id, uint256 amount, bytes memory data);
function safeTransferFrom(address from,address to,uint256 id,uint256 amount,bytes memory data); 
function burn(address account, uint256 id, uint256 value);
```

# 示例说明
示例路径：``test/java/com/echain/test/sdk``

|  示例名称   | 描述  |
|  ----  | ----  |
| TestAccount  | 生成随机账户地址、私钥 |
| TestSign  | 对标准`Erc1155`合约进行铸造、转移、销毁进行签名，得到交易哈希、签名后的交易体 |

示例路径：``test/java/com/echain/test/request``

|  示例名称   | 描述  |
|  ----  | ----  |
| TestQuery  | 查询区块号、交易收据、NFT余额的请求示例 |
| TestSendTx  | 发送交易的请求示例 |
| TestContractDeploy  | 部署标准``ERC1155``合约的请求示例 |

**注意：** 发送请求前需要先设置服务端证书路径（证书需要向亿链开发人员要一下），示例如下：
```
    static{
        HttpRequest.setServerCrtPath("D:\\yeepay\\e-chain.net.cn_server.crt");
    }
```
