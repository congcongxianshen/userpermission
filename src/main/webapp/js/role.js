
//var APP_PATH = ${APP_PATH };

var likeflg=false;
$(function () {
    $(".list-group-item").click(function () {
        if ($(this).find("ul")) {
            $(this).toggleClass("tree-closed");
            if ($(this).hasClass("tree-closed")) {
                $("ul", this).hide("fast");
            } else {
                $("ul", this).show("fast");
            }
        }
    });

    pageQuery(1); //默认查询首页

    $("#queryBtn").click(function () {
        var queryText = $("#queryText").val();
        console.log(queryText);
        if (queryText != "") {
            likeflg = true;
        } else {
            likeflg = false;
        }

        pageQuery(1);
    });

    $("tbody .btn-success").click(function () {
        window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function () {
        window.location.href = "edit.html";
    });
    $("#allSelBox").click(function () {
        var flg = this.checked; //获得单选框的状态
        console.log(flg);
        $("#userInfos :checkbox").each(function () {
            this.checked=flg;
        })
    });
});
    function pageQuery(pageno){
        var loadingIndex = null;
        var queryText = $("#queryText").val();
        var jsondata = {
            "pageno":pageno,
            "pagesize":5
        }
        if(likeflg){
            jsondata.queryText=queryText;
        }
        $.ajax({
            type:"POST",
            url:"/userpermission/role/getPageInfo",
            data:jsondata,
            beforeSend:function(){
                loadingIndex = layer.msg('处理中', {icon: 16});
            },
            success:function(result){
                layer.close(loadingIndex);
                if(result.success){
                    console.log(result);
                    var pageInfo = result.data;
                    var roleList = pageInfo.datas;
                    var roleContent="";
                    var pageContent="";
                    $.each(roleList,function(index,role){ //循环数据
                        roleContent+='<tr>'
                        roleContent+='<td>'+(index+1)+'</td>'
                        roleContent+=' <td><input type="checkbox" name="userids" value="'+role.id+'"></td>'
                        roleContent+=' <td>'+role.name+'</td>'
                        roleContent+='<td>'
                        roleContent+=' <button onclick="assignRole('+role.id+')" type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>'
                        roleContent+=' <button onclick="editUser('+role.id+')" type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>'
                        roleContent+=' <button onclick="removeUser('+role.id+', \''+role.name+'\')" type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>'
                        roleContent+='</td>'
                        roleContent+='</tr>'

                    });
                    $("#userInfos").html(roleContent);

                    if(pageno>1){ //存在上一页  "('2','89')"
                        pageContent+='<li onclick="toupperpage('+(pageno-1)+')"><a href="#">上一页</a></li>';
                    }
                    for(var i=1;i<=pageInfo.pagenototal;i++){
                        if(pageno==i){
                            pageContent+='<li onclick="tocurrentpage('+(i)+')" class="active"><a href="#">'+i+'</a></li>';
                        }else{
                            pageContent+='<li onclick="tocurrentpage('+(i)+')"><a href="#">'+i+'</a></li>';
                        }
                    }
                    if(pageno<pageInfo.pagenototal){ //存在上一页
                        pageContent+='<li onclick="tonextpage('+(pageno+1)+')"><a href="#">下一页</a></li>';
                    }

                    $(".pagination").html(pageContent);
                }else{
                    layer.msg("用户分页查询失败", {time:2000, icon:5, shift:6}, function(){
                    });
                }
            }
        });
    }
    function toupperpage(pageno){
        pageQuery(pageno)
    }
    function tonextpage(pageno){
        pageQuery(pageno)
    }
    function tocurrentpage(pageno){
        pageQuery(pageno)
    }
    function editUser(id) {
        console.log(id);
        if (id != null && id != '') {
            window.location.href = "/userpermission/user/toedit?id=" + id;
        }
    }
    function removeUser(id,username) {

        if(id != null && id !=''){
            var loadingIndex = null;
          layer.confirm('確定刪除【'+username+'】',  {icon: 3, title:'提示'}, function(cindex){
               $.ajax({
                   type:"POST",
                   url:"/userpermission/user/remove",
                   data:{
                       id:id
                   },
                   beforeSend:function(){
                       loadingIndex = layer.msg('处理中', {icon: 16});
                   },
                   success:function(result){

                       if(result.success){
                           layer.msg("用戶信息刪除成功", {time:2000, icon:6}, function(){
                               pageQuery(1);
                           });
                       }else{
                           layer.msg("用户信息刪除失败", {time:2000, icon:5, shift:6}, function(){
                           });
                       }
                   }
               });
              layer.close(loadingIndex);
            }, function(cindex){
              layer.close(loadingIndex);
            });
        }
    }
    function removeUsersInfo() {
        var boxs = $("#userInfos :checked");

        if(boxs.length == 0){
            layer.msg("请选择一个用户", {time:2000, icon:6}, function(){
                return;
            });
        }else{
            layer.confirm('確定刪除用户信息，请继续',  {icon: 3, title:'提示'}, function(cindex){
                var loadingIndex=null;
                $.ajax({
                    type:"POST",
                    url:"/userpermission/user/removes",
                    data:$("#userForm").serialize(),
                    beforeSend:function(){
                        loadingIndex = layer.msg('处理中', {icon: 16});
                    },
                    success:function(result){
                        layer.close(loadingIndex);
                        if(result.success){
                            layer.msg("用户信息刪除成功", {time:2000, icon:6}, function(){
                                pageQuery(1);
                            });
                        }else{
                            layer.msg("用户信息刪除失败", {time:2000, icon:5, shift:6}, function(){
                            });
                        }
                    }
                });
                layer.close(cindex);
            }, function(cindex){
                layer.close(cindex);
            });
        }
    }
    function assignRole(id){


        window.location.href="/userpermission/role/assignPermission?roleid="+id;

    }
