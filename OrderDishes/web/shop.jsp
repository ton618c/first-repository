<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜品管理</title>
    <script src="js/Vue.js"></script>
    <script src="js/index.js"></script>
    <script src="js/axios.js"></script>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
    <div id="box">
        <el-button type="primary" @click="openAddPanel">上传菜品</el-button>
        <el-table :data="shopList" style="width: 100%">
            <el-table-column prop="sname" label="菜品名称"></el-table-column>
            <el-table-column prop="price" label="单价"></el-table-column>
            <el-table-column prop="simage" label="菜品图片">
                <telmplate slot-scope="scope">
                    <img width="120px" height="40px" :src="scope.row.simage">
                </telmplate>
            </el-table-column>
            <el-table-column prop="status" label="是否库存">
                <telmplate slot-scope="scope">
                    <span v-if="scope.row.status == 1">是</span>
                    <span v-else>否</span>
                </telmplate>
            </el-table-column>
            <el-table-column prop="tname" label="类别名称"></el-table-column>
            <el-table-column fixed="right" label="操作" width="100">
                <template slot-scope="scope">
                    <el-button type="text" size="small" @click="openEditPanel(scope.row)">编辑</el-button>
                    <el-button type="text" size="small" @click="opendelPanel(scope.row.sid)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>

        <el-dialog title="删除提示框" :visible.sync="delVisible" width="30%">
            <span>确定删除么？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="delShop">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="上传菜品提示框" :visible.sync="addVisible" width="30%">
            <el-form :model="shop" label-width="80px">
                <el-form-item label="菜品名称">
                    <el-input v-model="shop.sname"></el-input>
                </el-form-item>
                <el-form-item label="单价">
                    <el-input v-model="shop.price"></el-input>
                </el-form-item>
                <el-form-item label="类别">
                    <el-select v-model="shop.tid" placeholder="请选择">
                        <el-option v-for="type in typeList" :label="type.tname" :value="type.tid"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="菜品图片">
                    <el-upload :http-request="pushImg" list-type="picture-card">
                        <i slot="default" class="el-icon-plus"></i>
                        <div slot="file" slot-scope="{file}">
                            <img class="el-upload-list__item-thumbnail" :src="file.url">
                        </div>
                    </el-upload>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="addShop">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="修改菜品提示框" :visible.sync="editVisible" width="30%">
            <el-form :model="shop" label-width="80px">
                <el-form-item label="菜品名称">
                    <el-input v-model="shop.sname"></el-input>
                </el-form-item>
                <el-form-item label="单价">
                    <el-input v-model="shop.price"></el-input>
                </el-form-item>
                <el-form-item label="是否有库存">
                    <el-radio-group v-model="shop.status">
                        <el-radio :label="1">是</el-radio>
                        <el-radio :label="2">否</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="类别">
                    <el-select v-model="shop.tid" placeholder="请选择">
                        <el-option v-for="type in typeList" :label="type.tname" :value="type.tid"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="editShop">确 定</el-button>
            </span>
        </el-dialog>
    </div>

    <script>
        new Vue({
            el:"#box",
            data:{
                editVisible:false,
                addVisible:false,
                delVisible:false,
                currentPage:1,
                pageSize:5,
                total:0,
                typeList:[],
                shopList:[],
                sid:"",
                shop:{},
                file:{}
            },
            methods:{
                selectAllShop:function () {
                    var currentPage = this.currentPage;
                    var pageSize = this.pageSize;
                    var that = this;
                    axios.post("shop?method=selectAllShop&currentPage="+currentPage+"&pageSize="+pageSize).then(function (res) {
                        if (res.data.status){
                            that.shopList = res.data.list;
                            that.total = res.data.total;
                        }else {
                            that.$message({message:res.data.message,type: 'warning'});
                        }
                    });
                },
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
                //当前页码数发生改变执行此函数
                handleCurrentChange:function (val) {
                    this.currentPage = val;
                    this.selectAllShop();
                },
                //一页显示数据条数发生改变
                handleSizeChange:function (val) {
                    this.pageSize = val;
                    this.selectAllShop();
                },
                opendelPanel:function (sid) {
                    this.delVisible = true;
                    this.sid = sid;
                },
                delShop:function () {
                    var sid = this.sid;
                    var that = this;
                    axios.post("shop?method=delShop&sid="+sid).then(function (res) {
                        if (res.data.status){
                            that.delVisible = false;
                            that.selectAllShop();
                        }else {
                            that.$message({message:res.data.message,type: 'warning'});
                            that.delVisible = false;
                        }
                    });
                },
                openAddPanel:function () {
                    this.addVisible = true;
                    this.shop = {};
                },
                addShop:function () {
                    //前后端交互数据，不可以是对象类型 shop:{sid:"1",sname:"aaa"}
                    var shop = this.shop;
                    var file = this.file;
                    //将对象类型转换为json类型 "shop":{"sid":""\1"\","sname":""\aaa"\"}
                    var shopJson = JSON.stringify(shop);
                    //js原生对象，用来存储map类型值 -- 底层是form表单传输数据格式
                    var formData = new FormData();
                    formData.append("shopJson",shopJson);
                    formData.append("file",file);
                    let option = {
                        url:"shop?method=addShop",
                        method:"POST",
                        data:formData
                    }
                    var that = this;
                    axios(option).then(function (res) {
                        if (res.data.status){
                            that.addVisible = false;
                            that.selectAllShop();
                        }else {
                            that.$message({message:res.data.message,type: 'warning'});
                            that.addVisible = false;
                        }
                    });
                },
                openEditPanel:function (shop) {
                    this.editVisible = true;
                    this.shop = shop;
                },
                editShop:function () {
                    var sid = this.shop.sid;
                    var sname = this.shop.sname;
                    var price = this.shop.price;
                    var status = this.shop.status;
                    var tid = this.shop.tid;
                    var that = this;
                    axios.post("shop?method=editShop&sid="+sid+"&sname="+sname+"&price="+price+"&tid="+tid+"&status="+status).then(function (res) {
                        if (res.data.status){
                            that.editVisible = false;
                            that.selectAllShop();
                        }else {
                            that.$message({message:res.data.message,type: 'warning'});
                            that.editVisible = false;
                        }
                    });
                },
                pushImg:function (param) {
                    this.file = param.file;
                }
            },
            mounted:function () {
                this.selectAllShop();
                this.selectAllType();
            }
        })
    </script>
</body>
</html>
