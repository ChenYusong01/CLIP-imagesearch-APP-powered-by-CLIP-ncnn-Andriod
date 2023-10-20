<<<<<<< HEAD
# -CLIP-APP-CLIP-ncnn-
将CLIP部署至安卓端，采用NCNN推理框架。
一、项目使用简介
  
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/63456f96-af75-46f6-af77-47887d48a3f1)

首先点击扫描相册获取当前相册内图片的时间信息同时解锁页面下部的按钮，接着点击一年（或所有）按钮对一年内（或所有）的图片进行特征提取，然后再输入框中输入对图片内容的描述（可以是单一关键词的组合或者一句描述的话），点击搜索按钮对键入文本特征进行提取同时比对图像特征（求余弦相似度），最终呈现出最佳匹配图片。






二、项目代码解析
（1）CLIP模型接口
 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/399767e5-3a69-4170-9c9b-ed0d86f1adb0)


（2）储存权限获取

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/2ad9ec47-e56d-4e83-a3d8-b373adf48e3a)


（3）获取相册图片时间信息
 
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/3de519ab-df4e-49c0-a650-b9b4ec0a47fa)

（4）解锁初始冻结的按钮

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/af97fa9d-f210-4cda-9b4b-0596bc8302f5)


（5）遍历提取选中所有图片特征
 
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/71888fed-2a58-4ecc-96fb-294514dfbbc1)

（6）提取键入文本特征

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/831544da-4ab8-4d56-ae00-6d0580eb869f)


（7）获取匹配度最高图片并显示
 
 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/359aac2e-f0e1-48fd-8414-03e67aed4486)



（8）界面设计
 
 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/7473dc09-5cba-4c55-a07f-ac0de8af3fc0)
 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/11bbbbd6-650b-473b-9db3-cdb30a45fa98)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/48325284-f143-4b70-aad9-d0bf4ce6b241)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/fa77a9ba-bc22-4eb6-8f02-fd8f28cd755e)


 
 
三、项目难点
1.Cmake编译ncnn框架源码
使用cmake工具对ncnn框架进行编译时总会爆发各种各样的错误，最后仔细查看日志发现大多数都是因为版本不适配造成的冲突，慢慢尝试不同的版本终于得到解决。另外，建议在Linux系统下进行编译。

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/e0c54241-517f-427b-b986-45ec65affd1c)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/c0b10d9f-63b9-4ed4-b1d3-f86561e5e892)
 
编译后得到：

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/16d354af-5530-4e25-a5a8-4afe7be4e2c6)

同时在opencv官网下载对应安卓的依赖包：

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/9f997bfb-b789-478c-b3d7-abd814c64dad)

二者均放置于安卓项目的CPP文件夹下：

 ![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/8f35762b-a87c-48ec-b6d5-2df468148f28)

2. CLIP模型PC端部署
参见：https://github.com/openai/CLIP#readme
      https://github.com/OFA-Sys/Chinese-CLIP#readme

3. CLIP模型格式的转换
CLIP模型本身是基于pytorch框架的，他的预训练模型格式与ncnn框架支持的格式不匹配，故需要进行对齐格式转换。
转换流程为： *.pth -> *.onnx -> *onnx-simplify -> bin & param
参考：https://github.com/Tencent/ncnn/wiki/use-ncnn-with-pytorch-or-onnx

![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/ed3b81e5-9cd2-427e-9d01-efbd06ad181b)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/9d1df45b-a714-4465-9d20-afb650e3d6c9)
![image](https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod/assets/91783123/db953f65-3613-432c-b5fd-148e06d28342)

#注意，转换后的onnx模型应当先进行精简，然后再转换为对应的bin模型。

4.根据ncnn框架官方文档编写对应的CLIP模型接口文件，并放置于CPP文件夹下。

参见：
https://github.com/Tencent/ncnn/wiki/how-to-build#build-for-android

https://github.com/Tencent/ncnn/wiki/use-ncnn-with-alexnet.zh

https://github.com/Tencent/ncnn/wiki/faq

=======
{"payload":{"allShortcutsEnabled":false,"fileTree":{"":{"items":[{"name":"README.md","path":"README.md","contentType":"file"}],"totalCount":1}},"fileTreeProcessingTime":7.472879,"foldersToFetch":[],"reducedMotionEnabled":null,"repo":{"id":707119964,"defaultBranch":"main","name":"CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-","ownerLogin":"ChenYusong01","currentUserCanPush":false,"isFork":false,"isEmpty":false,"createdAt":"2023-10-19T09:05:20.000Z","ownerAvatar":"https://avatars.githubusercontent.com/u/91783123?v=4","public":true,"private":false,"isOrgOwned":false},"symbolsExpanded":false,"treeExpanded":true,"refInfo":{"name":"main","listCacheKey":"v0:1697706321.8360078","canEdit":false,"refType":"branch","currentOid":"ba5341db0f55f382a4b941b279a38b895bb0b148"},"path":"README.md","currentUser":null,"blob":{"rawLines":null,"stylingDirectives":null,"csv":null,"csvError":null,"dependabotInfo":{"showConfigurationBanner":false,"configFilePath":null,"networkDependabotPath":"/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/network/updates","dismissConfigurationNoticePath":"/settings/dismiss-notice/dependabot_configuration_notice","configurationNoticeDismissed":null,"repoAlertsPath":"/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/security/dependabot","repoSecurityAndAnalysisPath":"/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/settings/security_analysis","repoOwnerIsOrg":false,"currentUserCanAdminRepo":false},"displayName":"README.md","displayUrl":"https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/blob/main/README.md?raw=true","headerInfo":{"blobSize":"4.97 KB","deleteInfo":{"deleteTooltip":"You must be signed in to make or propose changes"},"editInfo":{"editTooltip":"You must be signed in to make or propose changes"},"ghDesktopPath":"https://desktop.github.com","gitLfsPath":null,"onBranch":true,"shortPath":"54d903a","siteNavLoginPath":"/login?return_to=https%3A%2F%2Fgithub.com%2FChenYusong01%2FCLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-%2Fblob%2Fmain%2FREADME.md","isCSV":false,"isRichtext":true,"toc":[{"level":1,"text":"-CLIP-APP-CLIP-ncnn-","anchor":"-clip-app-clip-ncnn-","htmlText":"-CLIP-APP-CLIP-ncnn-"}],"lineInfo":{"truncatedLoc":"100","truncatedSloc":"52"},"mode":"file"},"image":false,"isCodeownersFile":null,"isPlain":false,"isValidLegacyIssueTemplate":false,"issueTemplateHelpUrl":"https://docs.github.com/articles/about-issue-and-pull-request-templates","issueTemplate":null,"discussionTemplate":null,"language":"Markdown","languageID":222,"large":false,"loggedIn":false,"newDiscussionPath":"/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/discussions/new","newIssuePath":"/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/issues/new","planSupportInfo":{"repoIsFork":null,"repoOwnedByCurrentUser":null,"requestFullPath":"/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/blob/main/README.md","showFreeOrgGatedFeatureMessage":null,"showPlanSupportBanner":null,"upgradeDataAttributes":null,"upgradePath":null},"publishBannersInfo":{"dismissActionNoticePath":"/settings/dismiss-notice/publish_action_from_dockerfile","dismissStackNoticePath":"/settings/dismiss-notice/publish_stack_from_file","releasePath":"/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/releases/new?marketplace=true","showPublishActionBanner":false,"showPublishStackBanner":false},"rawBlobUrl":"https://github.com/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/raw/main/README.md","renderImageOrRaw":false,"richText":"<article class=\"markdown-body entry-content container-lg\" itemprop=\"text\"><h1 tabindex=\"-1\" id=\"user-content--clip-app-clip-ncnn-\" dir=\"auto\"><a class=\"heading-link\" href=\"#-clip-app-clip-ncnn-\">-CLIP-APP-CLIP-ncnn-<svg class=\"octicon octicon-link\" viewBox=\"0 0 16 16\" version=\"1.1\" width=\"16\" height=\"16\" aria-hidden=\"true\"><path d=\"m7.775 3.275 1.25-1.25a3.5 3.5 0 1 1 4.95 4.95l-2.5 2.5a3.5 3.5 0 0 1-4.95 0 .751.751 0 0 1 .018-1.042.751.751 0 0 1 1.042-.018 1.998 1.998 0 0 0 2.83 0l2.5-2.5a2.002 2.002 0 0 0-2.83-2.83l-1.25 1.25a.751.751 0 0 1-1.042-.018.751.751 0 0 1-.018-1.042Zm-4.69 9.64a1.998 1.998 0 0 0 2.83 0l1.25-1.25a.751.751 0 0 1 1.042.018.751.751 0 0 1 .018 1.042l-1.25 1.25a3.5 3.5 0 1 1-4.95-4.95l2.5-2.5a3.5 3.5 0 0 1 4.95 0 .751.751 0 0 1-.018 1.042.751.751 0 0 1-1.042.018 1.998 1.998 0 0 0-2.83 0l-2.5 2.5a1.998 1.998 0 0 0 0 2.83Z\"></path></svg></a></h1>\n<p dir=\"auto\">将CLIP部署至安卓端，采用NCNN推理框架。\n一、项目使用简介</p>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276554724-63456f96-af75-46f6-af77-47887d48a3f1.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276554724-63456f96-af75-46f6-af77-47887d48a3f1.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">首先点击扫描相册获取当前相册内图片的时间信息同时解锁页面下部的按钮，接着点击一年（或所有）按钮对一年内（或所有）的图片进行特征提取，然后再输入框中输入对图片内容的描述（可以是单一关键词的组合或者一句描述的话），点击搜索按钮对键入文本特征进行提取同时比对图像特征（求余弦相似度），最终呈现出最佳匹配图片。</p>\n<p dir=\"auto\">二、项目代码解析\n（1）CLIP模型接口\n<a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276554845-399767e5-3a69-4170-9c9b-ed0d86f1adb0.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276554845-399767e5-3a69-4170-9c9b-ed0d86f1adb0.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">（2）储存权限获取</p>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276554872-2ad9ec47-e56d-4e83-a3d8-b373adf48e3a.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276554872-2ad9ec47-e56d-4e83-a3d8-b373adf48e3a.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">（3）获取相册图片时间信息</p>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276554899-3de519ab-df4e-49c0-a650-b9b4ec0a47fa.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276554899-3de519ab-df4e-49c0-a650-b9b4ec0a47fa.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">（4）解锁初始冻结的按钮</p>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276554928-af97fa9d-f210-4cda-9b4b-0596bc8302f5.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276554928-af97fa9d-f210-4cda-9b4b-0596bc8302f5.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">（5）遍历提取选中所有图片特征</p>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276554964-71888fed-2a58-4ecc-96fb-294514dfbbc1.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276554964-71888fed-2a58-4ecc-96fb-294514dfbbc1.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">（6）提取键入文本特征</p>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555003-831544da-4ab8-4d56-ae00-6d0580eb869f.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555003-831544da-4ab8-4d56-ae00-6d0580eb869f.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">（7）获取匹配度最高图片并显示</p>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555030-359aac2e-f0e1-48fd-8414-03e67aed4486.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555030-359aac2e-f0e1-48fd-8414-03e67aed4486.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">（8）界面设计</p>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555073-7473dc09-5cba-4c55-a07f-ac0de8af3fc0.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555073-7473dc09-5cba-4c55-a07f-ac0de8af3fc0.png\" alt=\"image\" style=\"max-width: 100%;\"></a>\n<a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555118-11bbbbd6-650b-473b-9db3-cdb30a45fa98.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555118-11bbbbd6-650b-473b-9db3-cdb30a45fa98.png\" alt=\"image\" style=\"max-width: 100%;\"></a>\n<a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555207-48325284-f143-4b70-aad9-d0bf4ce6b241.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555207-48325284-f143-4b70-aad9-d0bf4ce6b241.png\" alt=\"image\" style=\"max-width: 100%;\"></a>\n<a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555257-fa77a9ba-bc22-4eb6-8f02-fd8f28cd755e.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555257-fa77a9ba-bc22-4eb6-8f02-fd8f28cd755e.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">三、项目难点\n1.Cmake编译ncnn框架源码\n使用cmake工具对ncnn框架进行编译时总会爆发各种各样的错误，最后仔细查看日志发现大多数都是因为版本不适配造成的冲突，慢慢尝试不同的版本终于得到解决。另外，建议在Linux系统下进行编译。</p>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555288-e0c54241-517f-427b-b986-45ec65affd1c.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555288-e0c54241-517f-427b-b986-45ec65affd1c.png\" alt=\"image\" style=\"max-width: 100%;\"></a>\n<a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555324-c0b10d9f-63b9-4ed4-b1d3-f86561e5e892.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555324-c0b10d9f-63b9-4ed4-b1d3-f86561e5e892.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">编译后得到：</p>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555424-16d354af-5530-4e25-a5a8-4afe7be4e2c6.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555424-16d354af-5530-4e25-a5a8-4afe7be4e2c6.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">同时在opencv官网下载对应安卓的依赖包：</p>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555512-9f997bfb-b789-478c-b3d7-abd814c64dad.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555512-9f997bfb-b789-478c-b3d7-abd814c64dad.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">二者均放置于安卓项目的CPP文件夹下：</p>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555546-8f35762b-a87c-48ec-b6d5-2df468148f28.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555546-8f35762b-a87c-48ec-b6d5-2df468148f28.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<ol start=\"2\" dir=\"auto\">\n<li>\n<p dir=\"auto\">CLIP模型PC端部署\n参见：<a href=\"https://github.com/openai/CLIP#readme\">https://github.com/openai/CLIP#readme</a>\n<a href=\"https://github.com/OFA-Sys/Chinese-CLIP#readme\">https://github.com/OFA-Sys/Chinese-CLIP#readme</a></p>\n</li>\n<li>\n<p dir=\"auto\">CLIP模型格式的转换\nCLIP模型本身是基于pytorch框架的，他的预训练模型格式与ncnn框架支持的格式不匹配，故需要进行对齐格式转换。\n转换流程为： *.pth -&gt; *.onnx -&gt; *onnx-simplify -&gt; bin &amp; param\n参考：<a href=\"https://github.com/Tencent/ncnn/wiki/use-ncnn-with-pytorch-or-onnx\">https://github.com/Tencent/ncnn/wiki/use-ncnn-with-pytorch-or-onnx</a></p>\n</li>\n</ol>\n<p dir=\"auto\"><a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555711-ed3b81e5-9cd2-427e-9d01-efbd06ad181b.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555711-ed3b81e5-9cd2-427e-9d01-efbd06ad181b.png\" alt=\"image\" style=\"max-width: 100%;\"></a>\n<a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555742-9d1df45b-a714-4465-9d20-afb650e3d6c9.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555742-9d1df45b-a714-4465-9d20-afb650e3d6c9.png\" alt=\"image\" style=\"max-width: 100%;\"></a>\n<a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://user-images.githubusercontent.com/91783123/276555769-db953f65-3613-432c-b5fd-148e06d28342.png\"><img src=\"https://user-images.githubusercontent.com/91783123/276555769-db953f65-3613-432c-b5fd-148e06d28342.png\" alt=\"image\" style=\"max-width: 100%;\"></a></p>\n<p dir=\"auto\">#注意，转换后的onnx模型应当先进行精简，然后再转换为对应的bin模型。</p>\n<p dir=\"auto\">4.根据ncnn框架官方文档编写对应的CLIP模型接口文件，并放置于CPP文件夹下。</p>\n<p dir=\"auto\">参见：\n<a href=\"https://github.com/Tencent/ncnn/wiki/how-to-build#build-for-android\">https://github.com/Tencent/ncnn/wiki/how-to-build#build-for-android</a></p>\n<p dir=\"auto\"><a href=\"https://github.com/Tencent/ncnn/wiki/use-ncnn-with-alexnet.zh\">https://github.com/Tencent/ncnn/wiki/use-ncnn-with-alexnet.zh</a></p>\n<p dir=\"auto\"><a href=\"https://github.com/Tencent/ncnn/wiki/faq\">https://github.com/Tencent/ncnn/wiki/faq</a></p>\n</article>","renderedFileInfo":null,"shortPath":null,"tabSize":8,"topBannersInfo":{"overridingGlobalFundingFile":false,"globalPreferredFundingPath":null,"repoOwner":"ChenYusong01","repoName":"CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-","showInvalidCitationWarning":false,"citationHelpUrl":"https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-on-github/about-citation-files","showDependabotConfigurationBanner":false,"actionsOnboardingTip":null},"truncated":false,"viewable":true,"workflowRedirectUrl":null,"symbols":{"timedOut":false,"notAnalyzed":false,"symbols":[{"name":" -CLIP-APP-CLIP-ncnn-","kind":"section_1","identStart":1,"identEnd":22,"extentStart":0,"extentEnd":5090,"fullyQualifiedName":" -CLIP-APP-CLIP-ncnn-","identUtf16":{"start":{"lineNumber":0,"utf16Col":1},"end":{"lineNumber":0,"utf16Col":22}},"extentUtf16":{"start":{"lineNumber":0,"utf16Col":0},"end":{"lineNumber":100,"utf16Col":0}}}]}},"copilotInfo":null,"csrf_tokens":{"/ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/branches":{"post":"hV2KUqMmiYDXa9WaL6KyJcUPfTgNMYVxmq2naHwpJ4njcH31TkHuBdeWfzSNknKXIaejePOQa3Ens9BNOIQNXQ"},"/repos/preferences":{"post":"rMV-AUkHUEbNAODY7j6TRxzRgStAW8ZUJnbWOez4QJCYMUtgVZUKW6DLFmTWU1cgJf67ZZ4BNnaOxAYwdvnFPA"}}},"title":"CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-/README.md at main · ChenYusong01/CLIP-imagesearch-APP-powered-by-CLIP-ncnn-Andriod-"}
>>>>>>> c88d570 (更新代码文件以及项目说明)
