<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bzt.server.bo.ServerDiscSize"%>
<%@page import="com.bzt.server.bo.ServerInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>服务器详细统计表</TITLE>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<script type="text/javascript" src="/skin/wkmk/js/jquery-1.7.1.min.js"></script>
<script src="/public/js/echarts.js"></script>
<style type="text/css">
/*选项卡1*/
#Tab1{margin:0px;padding:0px;padding-left: 12px;}
/*菜单class*/
.Menubox_tab {width:100%;height:50px;line-height:50px;padding-top: 15px;}
.Menubox_tab ul{margin:0px;padding:0px;}
.Menubox_tab li{float:left;display:block;cursor:pointer;width:140px;height:50px;line-height:50px;text-align:center;color:#949694;font-weight:bold;}
.Menubox_tab li.hover{padding:0px;background:cornflowerblue;width:140px;color:#fff;font-weight:bold;height:50px;line-height:50px;font-family:castellar";}
.Contentbox_tab{width:100%;	}

-->
</style>
<SCRIPT language=javascript>
var color1='rgb(255, 70, 131)';
var color2='rgb(255, 158, 68)';//上部颜色
var color3='rgb(241, 247, 251)';//下部颜色
/*第一种形式 第二种形式 更换显示样式*/
function setTab(name,cursel,n){
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  menu.className=i==cursel?"hover":"";
  con.style.display=i==cursel?"block":"none";
 }
}

function searchRecord(){
  document.pageForm.action="/serverRunStatisticsAction.do?method=detailchart";
  document.pageForm.submit();
}

</SCRIPT>

</HEAD>

<BODY >
<FORM name="pageForm" method=post>
<TABLE width="98%" align="center">
  <TR>
    <TD class="page_title">服务器详细统计表</TD>
  </TR>
  <TR>
    <TD height="30">
      <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
               <td >系统管理>>服务器详细统计表</td>
            </tr>
       </table>
        <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">选择服务器：</td>
                    <td>
                    <select name="serverid" onchange="searchRecord()">
                    <%
                    	List serverInfos = (List)request.getAttribute("serverInfos");
                    	String serverid = (String)request.getAttribute("serverid");
                    	if(serverInfos!=null&&serverInfos.size()>0){
                    	ServerInfo serverInfo = null;
                    	for(int i=0;i<serverInfos.size();i++){
                    	serverInfo=(ServerInfo)serverInfos.get(i);
                    %>
                    <option value="<%= serverInfo.getId() %>" <%if(serverInfo.getId().equals(serverid)){ %>selected=true<%} %>><%= serverInfo.getServername()%></option>
                    <%}
                    } %>
                    </select>
                  </tr>
                </table>
              </td>
              </tr>
        </table>
    </TD>
  </TR>
  <TR>
    <TD class="page_blank"></TD>
  </TR>
</table>
<div id="Tab1">
    <div class="Menubox_tab">
    <ul>
         <% int diskSizeNum = (Integer)request.getAttribute("diskSizeNum");
        	List serverDiscSizes = (List)request.getAttribute("serverDiscSizes2");
        	ServerDiscSize  serverDiscSize =null;
        	%>
        <li id="one1" onclick="setTab('one',1,<%=diskSizeNum+3 %>)"  class="hover">CPU使用率</li>
        <li id="one2" onclick="setTab('one',2, <%=diskSizeNum+3 %>)" >物理内存使用率</li>
         <li id="one3" onclick="setTab('one',3, <%=diskSizeNum+3 %>)" >JVM内存使用率</li>
         <%  for(int i=0;i<diskSizeNum;i++){
                serverDiscSize = (ServerDiscSize)serverDiscSizes.get(i);
          %>
        	<li class="" id="one<%=i+4 %>" onclick="setTab('one',<%=i+4 %>, <%=diskSizeNum+3 %>)" ><%=serverDiscSize.getDriveletter() %>盘使用率</li>
        	<%
        	}
          %>
    </ul>
    </div>
        <div id="con_one_1" class="hover"> 
        <div class="review">
    	<p class="review_p"></p>
        <div class="review_div">
        	<div id="column1" style="width: 700px;height:300px;"></div>
        </div></div></div>
       
        <div id="con_one_2" style="display:none;width: 700px;height:300px;"> <div class="review">
    	<p class="review_p"></p>
        <div class="review_div">
        	<div id="column2" style="width: 700px;height:300px;"></div>
        </div></div>
        </div>
        <div id="con_one_3" style="display:none;width: 700px;height:300px;"> <div class="review">
    	<p class="review_p"></p>
        <div class="review_div">
        	<div id="column3" style="width: 700px;height:300px;"></div>
        </div></div>
         </div>
</div>
</form>
<script type="text/javascript">
 $(document).ready(function(){ 
 diskDiv(); 
 var chart = document.getElementById('column1');      
 var echart = echarts.init(chart);  
 var option = {
    tooltip: {
        trigger: 'axis',
        position: function (pt) {
            return [pt[0], '10%'];
        }
    },
    title: {
        left: 'center',
        text: '',
    },
    toolbox: {
        feature: {
            dataZoom: {
                yAxisIndex: 'none'
            }
        },
         right:70
    },
    calculable : true,
  	tooltip : {
	      trigger: 'axis',
	      formatter: "CPU使用率 :{c}%"
	    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ${timeArray}
    },
    yAxis: {
        type: 'value',
        boundaryGap: [0, '100%']
    },
    dataZoom: [{
        type: 'inside',
        start: 90,
        end: 100
    }, {
        start: 0,
        end: 10,
        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
        handleSize: '80%',
        handleStyle: {
            color: '#fff',
            shadowBlur: 3,
            shadowColor: 'rgba(0, 0, 0, 0.6)',
            shadowOffsetX: 2,
            shadowOffsetY: 2
        }
    }],
    series: [
        {
            name:'使用率',
            type:'line',
            smooth:true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: color1
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: color2
                    }, {
                        offset: 1,
                        color: color3
                    }])
                }
            },
            data: ${cpuData}
        }
    ]
};
echart.setOption(option);

      setColumn(2);
       setColumn(3);
       
       setDisk();
            }); 
  function setColumn(num){
  var dataValue=null;
  var dataName="";
  if(num==2){
  dataValue=${memData};
  dataName="物理内存";
  }
  if(num==3){
  dataValue=${jvmData};
  dataName="JVM内存";
  }
  var chart = document.getElementById('column'+num);      
 var echart = echarts.init(chart);  
 var option = {
    tooltip: {
        trigger: 'axis',
        position: function (pt) {
            return [pt[0], '10%'];
        }
    },
    title: {
        left: 'center',
        text: '',
    },
    toolbox: {
        feature: {
            dataZoom: {
                yAxisIndex: 'none'
            }
        },
         right:70
    },
    calculable : true,
  	tooltip : {
	      trigger: 'axis',
	      formatter: dataName+"使用率 :{c}%"
	    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ${timeArray}
    },
    yAxis: {
        type: 'value',
        boundaryGap: [0, '100%']
    },
    dataZoom: [{
        type: 'inside',
        start: 90,
        end: 100
    }, {
        start: 0,
        end: 10,
        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
        handleSize: '80%',
        handleStyle: {
            color: '#fff',
            shadowBlur: 3,
            shadowColor: 'rgba(0, 0, 0, 0.6)',
            shadowOffsetX: 2,
            shadowOffsetY: 2
        }
    }],
    series: [
        {
            name:'使用率',
            type:'line',
            smooth:true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: color1
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: color2
                    }, {
                        offset: 1,
                        color: color3
                    }])
                }
            },
            data: dataValue
        }
    ]
};
echart.setOption(option); 
  }
  
  function setDisk(){
  var diskSizeNum = ${diskSizeNum};
  var json = ${diskDataObj};
  var num=3;
   $.each(json, function (n, value) {
  var obj = value.split("||");
   var dataName=obj[0].replace("Data","盘");
   num+=1;
        //处理磁盘数据
    setDiskColumn(num,obj[1],dataName);
      });
  }
  function diskDiv(){
   var diskSizeNum = ${diskSizeNum};
  	var div = '';
	 for(var i=0;i<diskSizeNum;i++){
	         div+=  " <div id='con_one_"+(i+4)+"' style='display:none;width: 700px;height:300px;'>"+
					"<p class='review_p'></p>"+
				 "	<div id='column"+(i+4)+"' style='width: 700px;height:300px;'></div>"+
				"  </div>";
	 	    }
	document.getElementById('Tab1').innerHTML = document.getElementById('Tab1').innerHTML+div;
}
  function setDiskColumn(number,dataValue2,dataName2){
  var dataValue=null;
  var dataName="";
    dataValue = dataValue2.replace("[","").replace("]","");
    var strs= new Array();
	strs=dataValue.split(","); //字符分割 
    
    var datav=[];
    for(var a=0;a<strs.length;a++){
    datav.push(strs[a]);
    }
   dataName = dataName2;
  var divname ='column'+number;
  var chart = document.getElementById(divname);  
   var echart = echarts.init(chart);  
   var option = {
    tooltip: {
        trigger: 'axis',
        position: function (pt) {
            return [pt[0], '10%'];
        }
    },
    title: {
        left: 'center',
        text: '',
    },
    toolbox: {
        feature: {
            dataZoom: {
                yAxisIndex: 'none'
            }
        },
         right:70
    },
    calculable : true,
  	tooltip : {
	      trigger: 'axis',
	      formatter: dataName+"使用率 :{c}%"
	    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ${timeArray}
    },
    yAxis: {
        type: 'value',
        boundaryGap: [0, '100%']
    },
    dataZoom: [{
        type: 'inside',
        start: 90,
        end: 100
    }, {
        start: 0,
        end: 10,
        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
        handleSize: '80%',
        handleStyle: {
            color: '#fff',
            shadowBlur: 3,
            shadowColor: 'rgba(0, 0, 0, 0.6)',
            shadowOffsetX: 2,
            shadowOffsetY: 2
        }
    }],
    series: [
        {
            name:'使用率',
            type:'line',
            smooth:true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: color1
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: color2
                    }, {
                        offset: 1,
                        color: color3
                    }])
                }
            },
            data: datav
        }
    ]
};
echart.setOption(option); 
  }
  
  
  function  ajaxData(){
  	var diskSizeNum = ${diskSizeNum};
  	
  }
</script>
</BODY>
</html:html>
