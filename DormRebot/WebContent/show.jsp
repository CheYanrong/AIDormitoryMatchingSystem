<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
	<html>
	<head>
		<title>${name}</title>
		<style type="text/css">
			*{
				font-size: 36px;
			}
			a{
				text-decoration: none;
				color: #0204EE;
			}
			a:VISITED {
				color: #0204EE;
			}
			a:hover{
				color:red;
			}
			#content{
				width: 900px;
				height:50%;
				margin: 0 auto;
				overflow-y:scroll;
			}
			#content table{
				width: 100%;
				border-spacing: 20px;
			}
			#content .msg{
				border-radius:10px;
				padding: 10px 20px;
			}
			#content .logo{
				width: 120px;
				vertical-align: top;
			}
			#content .left{
				background-color: #eee;
			}
			#content .right{
				text-align: right;
				color:white;
				background-color: #0252AA;
			}
			#content img{
				height: 100px;
				border-radius:50px;
			}
			#input{
				width: 900px;
				margin: 0 auto;
				text-align: center;
			}
			#input .content{
				width: 80%;
				height: 80px;
				margin-left: 10px;
				margin-right: 10px;
				border-left: none;
				border-right: none;
				border-top: none;
				border-bottom: 2px solid #999;
			}
			#input .button{
				height: 80px;
				width: 80px;
				color: white;
			}
			#content .linkmsg{
				border: 1px solid;
				border-radius:15px;
				padding-left:15px;
				background-color: #44C8F9;
			}
			#content .linkmsg p{
				/* border: 1px solid; */
			}
			#content .linkmsg a{
				color: #0204EE;
			}
			#content .linkmsg a:VISITED{
				color: #0204EE;
			}
			#content .linkmsg a:ACTIVE{
				color: #0204EE;
			}
		</style>
		<script type="text/javascript">
			function initForm(){ //函数：获取尺寸
			    //获取浏览器窗口高度
			    var winHeight=0;
			    if (window.innerHeight)
			        winHeight = window.innerHeight;
			    else if ((document.body) && (document.body.clientHeight))
			        winHeight = document.body.clientHeight;
			    //通过Document对body进行检测，获取浏览器可视化高度
			    if (document.documentElement && document.documentElement.clientHeight)
			        winHeight = document.documentElement.clientHeight;
			    //DIV高度为浏览器窗口高度
			    //alert(winHeight);
			    var content=document.getElementById("content");
			    content.style.height= winHeight-130 +"px";
			    content.scrollTop = 99999;
			 
			}
			window.onresize=initForm; //窗口或框架被调整大小时执行
		</script>
	</head>
	<body onload="initForm()" >
		<div id="content" >
			<table>
				<c:forEach var="contente" items="${contentes}">
					<tr>
						<c:if test="${contente.type==1 }">
							<td class="logo">
								<c:if test="${not empty contente.sendTime}">
									<img src="img/female.gif">
								</c:if>
							</td>
							<td class="left msg">
								<p><fmt:formatDate value="${contente.sendTime}" pattern="MM-dd hh:mm"/></p>
								<p>${contente.msg}<p>
							</td>
							<td></td>
						</c:if>
						<c:if test="${contente.type==0 }">
							<td></td>
							<td class="right msg">${contente.msg}</td>
							<td class="logo">
								<img src="img/male.gif">
							</td>
						</c:if>
						<c:if test="${contente.type==2 }">
							<td class="logo">
							</td>
							<td class="left linkmsg">
								<c:forEach var="link" items="${contente.messages}">
									<p><a href="${link.url}">${link.text}</a><p>
								</c:forEach>
							</td>
							<td></td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="input">
			<form action="BaseServlet" method="post">
				<input class="content" type="text" name="content">
				<input class="button" type="image" src="img/button.gif" >
			</form>
		</div>
		<ul>
		</ul>
	</body>
</html>