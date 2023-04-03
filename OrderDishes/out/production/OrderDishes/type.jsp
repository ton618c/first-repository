<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>类别管理</title>
    <script src="js/Vue.js"></script>
    <script src="js/index.js"></script>
    <script src="js/axios.js"></script>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
    <div id="box">
        <el-button type="primary" @click="openAddPanel">创建类别</el-button>
        <el-table :data="typeList" style="width: 100%">
            <el-table-column prop="tname" label="类别名称"></el-table-column>
            <el-table-column prop="ttime" label="创建日期"></el-table-column>
            <el-table-column fixed="right" label="操作" width="100">
                <template slot-scope="scope">
                    <el-button type="text" size="small" @click="openEditPanel(scope.row)">编辑</el-button>
                    <el-button type="text" size="small" @click="opendelPanel(scope.row.tid)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="创建类别提示框" :visible.sync="addVisible" width="30%">
            <el-form :model="type" label-width="80px">
                <el-form-item label="类别名称">
                    <el-input v-model="type.tname"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="addType">确 定</el-button>
            </span>
        </el-dialog>


        <el-dialog title="删除提示框" :visible.sync="delVisible" width="30%">
            <span>确定删除么？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="delType">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="修改类别提示框" :visible.sync="editVisible" width="30%">
            <el-form :model="type" label-width="80px">
                <el-form-item label="类别名称">
                    <el-input v-model="type.tname"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="editType">确 定</el-button>
            </span>
        </el-dialog>

    </div>

    <script>
        new Vue({
            el:"#box",
            data:{
                addVisible:false,
                delVisible:false,
                editVisible:false,
                tid:"",
                type:{
                    tid:"",
                    tname:"",
                    ttime:""
                },
                typeList:[]
            },
            methods:{
                selectAllType:function(){
                    var that = this;
                    axios.post("type?method=selectAllType").then(function (res) {
                        if (res.data.status){
                            that.typeList = res.data.list1;
                        }else {
                            that.$message({message:res.data.message,type: 'warning'});
                        }
                    });
                },
                openAddPanel:function () {
                    this.addVisible = true;
                    this.type.tname = "";
                },
                addType:function () {
                    var tname = this.type.tname;
                    var that = this;
                    axios.post("type?method=addType&tname="+tname).then(function (res) {
                        if (res.data.status){
                            that.selectAllType();
                            that.addVisible = false;
                        }else {
                            that.$message({message:res.data.message,type: 'warning'});
                            that.delVisible = false;
                        }
                    });
                },
                opendelPanel:function(tid){
                    this.delVisible = true;
                    this.tid = tid;
                },
                delType:function(){
                    var tid = this.tid;
                    var that = this;
                    axios.post("type?method=delType&tid="+tid).then(function (res) {
                        if (res.data.status){
                            that.selectAllType();
                            that.delVisible = false;
                        }else {
                            that.$message({message:res.data.message,type: 'warning'});
                            that.delVisible = false;
                        }
                    });
                },
                openEditPanel:function (type) {
                    this.editVisible = true;
                    this.type = type;
                },
                editType:function () {
                    // 未实现，在没有springMvc情况下，request无法直接接收流数据
                    // 前后端交互数据，不可以是对象类型 type:{tid:"1",tname:"aaa"}
                    // var type = this.type;
                    // //将对象类型转换为json类型 "type":{"tid":""\1"\","tname":""\aaa"\"}
                    // var typeJson = JSON.stringify(type);
                    // //js原生对象，用来存储map类型值
                    // var formData = new FormData();
                    // formData.append("typeJson",typeJson);
                    // let option = {
                    //     url:"type?method=editType",
                    //     method:"POST",
                    //     data:formData
                    // }
                    // var that = this;
                    // axios(option).then(function (res) {
                    //     if (res.data.status){
                    //         that.selectAllType();
                    //         that.editVisible = false;
                    //     }else {
                    //         that.$message({message:res.data.message,type: 'warning'});
                    //     }
                    // });
                    var tid = this.type.tid;
                    var tname = this.type.tname;
                    var that = this;
                    axios.post("type?method=editType&tid="+tid+"&tname="+tname).then(function (res) {
                        if (res.data.status){
                            that.selectAllType();
                            that.editVisible = false;
                        }else {
                            that.$message({message:res.data.message,type: 'warning'});
                            that.delVisible = false;
                        }
                    });
                }
            },
            mounted:function () {//生命周期函数 -- 挂载结束后执行
                this.selectAllType();
            }
        })
    </script>
</body>
</html>
