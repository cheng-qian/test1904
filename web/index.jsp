<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>省市级联查询</title>
      <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
      <script type="text/javascript">
        $(function () {
           $("#btnLoad").click(function(){
                $.ajax({
                  url:"queryProvince",
                  dataType:"json",
                  success:function (resp) {
                    $("#province").empty();
                    $.each( resp,function(i,n){
                       $("#province").append("<option value='"+n.id+"'>"+n.name+"</option>")
                    } )
                  }
              })
           })

          $("#province").change(function () {
            var obj = $("#province>option:selected");
             //alert("select的change事件"+ obj.val() +"===="+obj.text())
           var provinceId =  obj.val();
           //alert(provinceId)
          $.post("queryCity",{proid:provinceId},function(resp) {
              $("#city").empty();
              $.each(resp,function (i,n) {
                $("#city").append("option value= '"+n.id+"'>"+n.name+"</option>")
              })
          },"json");

          })
        })
      </script>
  </head>
  <body>
    <p>省市级联查询，使用ajax</p>
    <div>
      <table border="1" cellpadding="0" cellspacing="0">
        <tr>
          <td>省份:</td>
          <td>
            <select id="province">
              <option value = "0">请选择.....</option>
            </select>
            <input type="button" value="load数据" id="btnLoad"/>
          </td>
        </tr>
        <tr>
          <td>城市：</td>
          <td>
          <select id="city">
            <option value="0">请选择.....</option>
          </select>
          </td>
        </tr>
      </table>
    </div>
  </body>
</html>
