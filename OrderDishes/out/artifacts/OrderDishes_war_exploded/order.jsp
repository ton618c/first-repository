<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单管理</title>
    <script src="js/Vue.js"></script>
    <script src="js/index.js"></script>
    <script src="js/axios.js"></script>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<div id="box">
    <el-table :data="orderList" style="width: 100%">
        <el-table-column prop="user.name" label="客户"></el-table-column>
        <el-table-column prop="ordertime" label="下单时间"></el-table-column>
        <el-table-column prop="status" label="送餐状态">
            <telmplate slot-scope="scope">
                <span v-if="scope.row.status == 1">待送达</span>
                <span v-else>已送达</span>
            </telmplate>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
                <el-button type="text" size="small" @click="openDetailPanel(scope.row.oid)">详情</el-button>
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
</div>
<script>
    new Vue({
        el:"#box",
        data:{
            orderList:[],
            currentPage:1,
            pageSize:5,
            total:0
        },
        methods:{
            selectAllOrder(){
                var currentPage = this.currentPage;
                var pageSize = this.pageSize;
                var that = this;
                axios.post("order?method=selectAllOrder&currentPage="+currentPage+"&pageSize="+pageSize).then(function (res) {
                    if (res.data.status){
                        that.orderList = res.data.list;
                        that.total = res.data.total;
                    }else {
                        that.$message({message:res.data.message,type: 'warning'});
                    }
                });
            },
            //当前页码数发生改变执行此函数
            handleCurrentChange:function (val) {
                this.currentPage = val;
                this.selectAllOrder();
            },
            //一页显示数据条数发生改变
            handleSizeChange:function (val) {
                this.pageSize = val;
                this.selectAllOrder();
            },
            openDetailPanel:function (oid) {

            }
        },
        mounted:function () {
            this.selectAllOrder();
        }
    })
</script>
</body>
</html>