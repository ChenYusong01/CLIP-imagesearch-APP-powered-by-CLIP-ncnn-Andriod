# -CLIP-APP-CLIP-ncnn-
将CLIP部署至安卓端，采用NCNN推理框架。

一、项目使用简介
  
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/e1d5bba0-65d3-461d-b1e6-fefd65a9cbf6)


首先点击扫描相册获取当前相册内图片的时间信息同时解锁页面下部的按钮，接着点击一年（或所有）按钮对一年内（或所有）的图片进行特征提取，然后再输入框中输入对图片内容的描述（可以是单一关键词的组合或者一句描述的话），点击搜索按钮对键入文本特征进行提取同时比对图像特征（求余弦相似度），最终呈现出最佳匹配图片。



二、项目代码解析

（1）CLIP模型接口

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/082ed3f4-4d6c-4deb-b09d-94ed947deef6)


（2）储存权限获取

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/75df53bf-b562-4aeb-8ecd-742097a616a3)



（3）获取相册图片时间信息

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/60c6b5e9-a145-43d1-935b-0fe0e81b14e8)


（4）解锁初始冻结的按钮

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/2d5aaeeb-ae1e-46d0-94f4-f87469650a4c)


（5）遍历提取选中所有图片特征

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/91535e32-bb16-4f8d-a804-62008133a8dd)


（6）提取键入文本特征

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/a3aa66f4-1bca-4391-8857-00d91d6a58f7)


（7）获取匹配度最高图片并显示
 
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/1df28945-2f48-4bb3-8f47-544aea92b716)

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/9a5c4c32-d5bc-442c-aaee-1100a5aea14f)



（8）界面设计
 
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/370e90de-531f-471b-8bd1-72adab8960aa)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/106a7d3a-765d-40c7-b84c-c0b1739a025e)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/88480f3c-fce4-467a-8241-425b16c0524e)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/2d16e82a-1d85-42ce-a689-128fa1bb4812)

 
 
三、项目难点

1.Cmake编译ncnn框架源码

使用cmake工具对ncnn框架进行编译时总会爆发各种各样的错误，最后仔细查看日志发现大多数都是因为版本不适配造成的冲突，慢慢尝试不同的版本终于得到解决。另外，建议在Linux系统下进行编译。

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/16184cdb-9f2f-4fc4-b35c-c20581c200c7)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/80ff3d0c-286b-44cd-89d3-c125692fbd00)

 
编译后得到：

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/6a076a83-7a6a-4fef-806d-c965e37e5e54)

同时在opencv官网下载对应安卓的依赖包：

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/954f3d88-14ab-4570-84b3-7abd343d1d85)

二者均放置于安卓项目的CPP文件夹下：

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/bc68712a-dc83-49af-a490-376ba07c4f05)

2. CLIP模型PC端部署
   
参见：
https://github.com/openai/CLIP#readme
https://github.com/OFA-Sys/Chinese-CLIP#readme

3. CLIP模型格式的转换
CLIP模型本身是基于pytorch框架的，他的预训练模型格式与ncnn框架支持的格式不匹配，故需要进行对齐格式转换。
转换流程为： *.pth -> *.onnx -> *onnx-simplify -> bin & param

参考：https://github.com/Tencent/ncnn/wiki/use-ncnn-with-pytorch-or-onnx

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/00cd67fc-e9d1-4ea4-a311-39b4dbf059c8)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/96217e0e-f677-4635-8172-ebf129dfa181)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/e95d406e-8835-4e2c-9f13-3636b3526220)

#注意，转换后的onnx模型应当先进行精简，然后再转换为对应的bin模型。

4.根据ncnn框架官方文档编写对应的CLIP模型接口文件，并放置于CPP文件夹下。

参见：

https://github.com/Tencent/ncnn/wiki/how-to-build#build-for-android

https://github.com/Tencent/ncnn/wiki/use-ncnn-with-alexnet.zh

https://github.com/Tencent/ncnn/wiki/faq

