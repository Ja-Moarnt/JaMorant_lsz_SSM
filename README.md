# 随手买-随车智能营销平台 

> **作者：刘绍珍**

**随手买-随车智能营销平台 后端项目GitHub仓库地址:**

**随手买-随车智能营销平台 前端项目GitHub仓库地址:**

**随手买-随车智能营销平台 开发文档百度云盘地址:**  https://pan.baidu.com/s/184S5r_6Kh3zorVcOSbDVUg?pwd=1234

**随手买-随车智能营销平台 项目演示视频百度云盘地址:**   https://pan.baidu.com/s/1SM3l6KjD2NTdTgfT_vrq9w?pwd=1234

### 项目背景

​		近年来，网约车行业快速发展，成为人们日常出行的重要方式之一。随着新能源汽车的普及和城市服务体系的完善，网约车市场需求不断增加。同时，消费者在乘车出行中需要购买各种物品，例如饮料、小食品、口罩等，但传统的超市购物需要下车进入商店，不仅浪费时间，也不够便捷。为此，我们提出了“随手买-随车智能营销平台”项目。

![image-20230531215303244](C:\Users\江理彭于晏\AppData\Roaming\Typora\typora-user-images\image-20230531215303244.png)

​		“随手买-智能营销平台”旨在联系广大乘车消费者、新能源汽车车主和城市囤货场，通过智能商品营销平台，实现乘车出行消费者在车内自助购买物品并自动支付、新能源汽车车主获取佣金，并且可于附近囤货场联系，预约补货。该平台为城市出行带来了更加便捷、高效的消费体验，同时为新能源汽车车主提供了增加收入的机会，为囤货场提供了更加广泛的销售渠道，具有良好的市场前景。

### 问题解决

![image-20230531215446049](C:\Users\江理彭于晏\AppData\Roaming\Typora\typora-user-images\image-20230531215446049.png)

![image-20230531215528083](C:\Users\江理彭于晏\AppData\Roaming\Typora\typora-user-images\image-20230531215528083.png)

![image-20230531215540222](C:\Users\江理彭于晏\AppData\Roaming\Typora\typora-user-images\image-20230531215540222.png)

![image-20230531215548312](C:\Users\江理彭于晏\AppData\Roaming\Typora\typora-user-images\image-20230531215548312.png)

![image-20230531215600432](C:\Users\江理彭于晏\AppData\Roaming\Typora\typora-user-images\image-20230531215600432.png)

### 技术架构

​		该项目的前端用到的技术有 vue.js，webSocket 和 echarts 等，后台使用的技术有 SpingCloud 2.2.0 ，Spring Boot 2.2.1 ，nacos 服务注册，Gateway网关，https 内网穿透，腾讯云域名，腾讯云对象存储 API，腾讯云与点播API，swagger 接口文档，Mybatis-Plus 操作数据库，easyexcel 表格操作，jwt 生成 token、微信小程序授权支付接口，openfeign 远程调用



**1.Vue.js** 是一个轻量级、高性能的 JavaScript 框架。它采用虚拟 DOM技术，使得在数据变化时只会重新渲染部分组件，减少不必要的 DOM 操作，提高渲染效率。另外，Vue.js 也支持异步组件和代码分割，可以将组件按需加载，避免了一次性加载所有组件造成的性能损耗。此外，Vue.js还提供了缓存和编译优化等功能，可以大幅提高应用的性能表现。总之，使用 Vue.js 开发应用可以获得较高的性能表现。但是，具体的性能表现还取决于具体的应用场景和开发实践。



**2.WebSockets** 是一种现代的网络协议，它提供了一种实时双向通信机制，可以使 Web 应用程序更加交互和动态。



**3.ECharts** 是一个基于 JavaScript 的开源可视化库，可以用于数据可视化。它的特点有多样化的图表类型：ECharts 提供了多种类型的图表，包括折线图、柱状图、饼图、散点图、雷达图、地图等，可以满足不同场景下的数据可视化需求；可扩展性：ECharts 提供了插件机制，可以通过插件扩展 ECharts 的功能，例如添加自定义的图表类型或者自定义交互行为等；易于使用：ECharts 提供了简单易用的 API，可以快速实现图表的生成和配置，同时也提供了丰富的文档和示例，方便开发者上手；高度可定制化：ECharts 的图表可以进行高度定制，可以通过配置项修改图表的样式、数据、交互行为等，满足个性化的需求；兼容性好：ECharts 兼容主流的浏览器，同时也提供了移动端的支持，可以适应不同的平台。



**4.SpringCloud 2.2.0 和 Spring Boot 2.2.1**：这两个框架是目前 Java 开发中非常流行的微服务框架，能够提高代码复用性、可维护性、可扩展性，为企业级应用提供了完善的解决方案。其相互配合，能够快速搭建分布式系统架构，具备高可用性和高并发性能。



**5.nacos 服务注册**： Nacos 是一个开源的服务发现和配置管理平台，提供了服务注册、配置管理、DNS 和 HTTP&gRPC 调用等一系列服务，具备高可用和可扩展的特性。它能够帮助我们更好地实现服务的注册与发现、配置的动态更新等功能，从而提高整个系统的可靠性和可维护性。



**6.Gateway 网关**： Gateway 是 Spring Cloud 生态系统中的一个 API 网关，它能够提供基于路由的访问控制、请求转发、负载均衡、熔断器等一系列功能。使用 Gateway 可以使得我们的服务更加安全可靠、可扩展性强。



**7.https 内网穿透**： 使用 https 协议的内网穿透可以让我们在开发阶段就能够提前解决网络请求的问题，使得服务调用更加可靠和稳定。



**8.腾讯云域名、腾讯云对象存储 API、腾讯云与点播 API**： 腾讯云提供的一系列云服务能够为我们提供丰富的云计算资源，包括域名解析、对象存储、视频点播等功能，可以为我们的开发提供更加丰富的资源选择。



**9.swagger 接口文档**： Swagger 是一个用于设计、构建、记录和使用RESTful API 的开源工具。使用 Swagger 可以使得我们的 API 文档更加规范化和规范化，让我们的 API 更加易于开发和使用。



**10.Mybatis-Plus 操作数据库**： Mybatis-Plus 是 Mybatis 的增强工具，它在 Mybatis 的基础上进一步扩展了 Mybatis 的功能，提供了更加便捷和高效的操作数据库的方式，为我们的开发提供了更好的支持。



**11.easyexcel 表格操作**： EasyExcel 是一个简单、快速、高效的 Excel读写解决方案，它能够帮助我们更加便捷地操作 Excel 表格数据，提高数据的导入导出效率。



**12.jwt 生成 token**： JWT（JSON Web Token）是一种用于双方之间安全传递信息的简洁、轻巧的方式。使用 JWT 生成 token 可以提高系统的安全性和可维护性。



**13.OpenFeign** 远程调用可以方便地进行微服务之间的通信，提高了服务之间的互操作性和可维护性。 



### 模块设计



![image-20230531220525887](C:\Users\江理彭于晏\AppData\Roaming\Typora\typora-user-images\image-20230531220525887.png)

***\*设备管理模块\****：主要负责管理设备的注册、激活、定位和状态监控等功能，确保设备正常运行。

***\*乘客购买模块\****：提供商品购买服务，包括商品展示、优惠券查询和领取、下单、支付、订单查询等功能。

***\*车主端模块\****：提供车主相关服务，包括设备注册、激活、车辆商品查询、订单查询、补货申请等功能。

***\*囤货场端模块\****：提供囤货场管理人员相关服务，包括囤货场信息完善、邀约车主、补货申请审核等功能。

***\*平台管理模块\****：提供平台管理人员相关服务，包括平台管理端包括统计分析、车主管理、乘客管理、囤货场管理、数据集管理、商品管理、广告管理、车辆管理、车辆货物管理、订单管理、优惠券管理，负责项目数据的管理。

***\*乘客消费习惯及推荐算法研究模块\****：分析乘客消费习惯，提供商品推荐服务，提高乘客购买体验。

### 数据库设计

![image-20230531220420134](C:\Users\江理彭于晏\AppData\Roaming\Typora\typora-user-images\image-20230531220420134.png)

### **结语**

​		随手买是一款创新的网约车智能营销平台，通过结合网约车服务和智能零售的概念，为乘客、车主和囤货场提供了全新的服务和商业机会。在技术方面，随手买采用了分布式微服务和云技术，实现了系统的高并发、高可用和高扩展性。随着网约车市场的不断扩大，随手买的应用场景也在不断增多，有着广阔的市场前景。