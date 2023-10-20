# -CLIP-APP-CLIP-ncnn-
将CLIP部署至安卓端，采用NCNN推理框架。
一、项目使用简介
  
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/63456f96-af75-46f6-af77-47887d48a3f1)

首先点击扫描相册获取当前相册内图片的时间信息同时解锁页面下部的按钮，接着点击一年（或所有）按钮对一年内（或所有）的图片进行特征提取，然后再输入框中输入对图片内容的描述（可以是单一关键词的组合或者一句描述的话），点击搜索按钮对键入文本特征进行提取同时比对图像特征（求余弦相似度），最终呈现出最佳匹配图片。






二、项目代码解析
（1）CLIP模型接口
 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/399767e5-3a69-4170-9c9b-ed0d86f1adb0)


（2）储存权限获取

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/2ad9ec47-e56d-4e83-a3d8-b373adf48e3a)


（3）获取相册图片时间信息
 
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/3de519ab-df4e-49c0-a650-b9b4ec0a47fa)

（4）解锁初始冻结的按钮

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/af97fa9d-f210-4cda-9b4b-0596bc8302f5)


（5）遍历提取选中所有图片特征
 
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/71888fed-2a58-4ecc-96fb-294514dfbbc1)

（6）提取键入文本特征

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/831544da-4ab8-4d56-ae00-6d0580eb869f)


（7）获取匹配度最高图片并显示
 
 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/359aac2e-f0e1-48fd-8414-03e67aed4486)



（8）界面设计
 
 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/7473dc09-5cba-4c55-a07f-ac0de8af3fc0)
 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/11bbbbd6-650b-473b-9db3-cdb30a45fa98)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/48325284-f143-4b70-aad9-d0bf4ce6b241)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/fa77a9ba-bc22-4eb6-8f02-fd8f28cd755e)


 
 
三、项目难点
1.Cmake编译ncnn框架源码
使用cmake工具对ncnn框架进行编译时总会爆发各种各样的错误，最后仔细查看日志发现大多数都是因为版本不适配造成的冲突，慢慢尝试不同的版本终于得到解决。另外，建议在Linux系统下进行编译。

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/e0c54241-517f-427b-b986-45ec65affd1c)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/c0b10d9f-63b9-4ed4-b1d3-f86561e5e892)
 
编译后得到：

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/16d354af-5530-4e25-a5a8-4afe7be4e2c6)

同时在opencv官网下载对应安卓的依赖包：

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/9f997bfb-b789-478c-b3d7-abd814c64dad)

二者均放置于安卓项目的CPP文件夹下：

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/8f35762b-a87c-48ec-b6d5-2df468148f28)

2. CLIP模型PC端部署
参见：https://github.com/openai/CLIP#readme
      https://github.com/OFA-Sys/Chinese-CLIP#readme

3. CLIP模型格式的转换
CLIP模型本身是基于pytorch框架的，他的预训练模型格式与ncnn框架支持的格式不匹配，故需要进行对齐格式转换。
转换流程为： *.pth -> *.onnx -> *onnx-simplify -> bin & param
参考：https://github.com/Tencent/ncnn/wiki/use-ncnn-with-pytorch-or-onnx

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/ed3b81e5-9cd2-427e-9d01-efbd06ad181b)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/9d1df45b-a714-4465-9d20-afb650e3d6c9)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/assets/91783123/db953f65-3613-432c-b5fd-148e06d28342)

#注意，转换后的onnx模型应当先进行精简，然后再转换为对应的bin模型。

4.根据ncnn框架官方文档编写对应的CLIP模型接口文件，并放置于CPP文件夹下。

参见：
https://github.com/Tencent/ncnn/wiki/how-to-build#build-for-android

https://github.com/Tencent/ncnn/wiki/use-ncnn-with-alexnet.zh

https://github.com/Tencent/ncnn/wiki/faq

