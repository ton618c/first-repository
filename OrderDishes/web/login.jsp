<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script src="js/Vue.js"></script>
    <script src="js/index.js"></script>
    <script src="js/axios.js"></script>
    <link rel="stylesheet" href="css/index.css">
    <style>
        *{margin: 0px;padding: 0px}
        body{
            background-image: url("images/neijuan.jpg");
            background-repeat: no-repeat;
            background-size: cover;
        }
        #box{
            width: 300px;
            margin-top: 300px;
            margin-left: 800px;
            border:1px solid black;
            border-radius: 10px;
            background: #e6f1fe;
        }
        h1{
            text-align: center;
            height: 40px;
            line-height: 40px;
            color: red;
        }
        .el-button{
            margin-left: 110px;
        }
    </style>
</head>
<body>
    <div id="box">
        <el-form :inline="true" ref="user" :model="user" :rules="rules" label-width="80px">
            <h1>信阳菜馆</h1>
            <el-form-item label="账号" prop="tel">
                <el-input v-model="user.tel"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="user.password"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="login('user')">登录</el-button>
            </el-form-item>
        </el-form>
    </div>

    <script>
        new Vue({
            el:"#box",
            data:{
                user:{
                    tel:"",
                    password:""
                },
                rules:{
                    tel: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                        { min: 3, max: 11, message: '长度在 3 到 11 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
                    ]
                }
            },
            methods:{
                login:function (user) {
                    this.$refs[user].validate((valid) => {
                        if (valid) {
                            var tel = this.user.tel;
                            var password = this.user.password;
                            var that = this;
                            //res是后端给前端的响应 -- res:{status:false,message:"账号输入有误！"}
                            axios.post("user?method=login&tel="+tel+"&password="+password).then(function (res) {
                                if(res.data.status){
                                    location.href = "index.jsp";
                                }else{
                                    that.$message({message:res.data.message,type: 'warning'});
                                }
                            });
                        } else {
                            this.$message.warning('请按规则填写账号密码！');
                        }
                    });
                }
            }
        })
    </script>
</body>
</html>
