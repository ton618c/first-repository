<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>主页</title>
    <script src="js/Vue.js"></script>
    <script src="js/index.js"></script>
    <link rel="stylesheet" href="css/index.css">
    <style>
      *{margin: 0px;padding: 0px;}
      /*css样式不可以设置任何元素高度为100%，只有在elementui如下使用才能实现*/
      html,body,#box,.el-container,.el-menu{
        height: 100%;
      }
      iframe{
        height: 98%;
      }
      .el-header{
        background-color: #000088;
        color: #333;
        text-align: center;
        line-height: 60px;
      }
      .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
      }
      .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px;
      }
      body > .el-container {
        margin-bottom: 40px;
      }
      h1{
        float: left;
        color: #ffffff;
      }
      .el-button{
        float: right;
        height: 60px;
        font-size: 24px;
      }
      a{
        color: #ffffff;
        text-decoration: none;
      }
    </style>
  </head>
  <body>
    <div id="box">
      <el-container>
        <el-header>
          <h1>点餐管理系统</h1>
          <el-button type="text">注销</el-button>
        </el-header>
        <el-container>
          <el-aside width="200px">
            <el-menu default-active="1"
                    class="el-menu-vertical-demo"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b">
              <el-menu-item index="1">
                <%--点击a标签进入type页面，页面打开方式在iframe窗口中打开--%>
                <a href="type.jsp" target="main">
                  <i class="el-icon-s-grid"></i>
                  <span slot="title">类别管理</span>
                </a>
              </el-menu-item>
              <el-menu-item index="2">
                <a href="shop.jsp" target="main">
                  <i class="el-icon-s-shop"></i>
                  <span slot="title">菜品管理</span>
                </a>
              </el-menu-item>
              <el-menu-item index="3">
                <a href="order.jsp" target="main">
                  <i class="el-icon-s-order"></i>
                  <span slot="title">订单管理</span>
                </a>
              </el-menu-item>
            </el-menu>
          </el-aside>
          <el-main>
            <%--在一个页面中嵌套其他页面--%>
            <iframe name="main" width="95%" frameborder="0"></iframe>
          </el-main>
        </el-container>
      </el-container>
    </div>

    <script>
      new Vue({
        el:"#box"
      })
    </script>
  </body>
</html>