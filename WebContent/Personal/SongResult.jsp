<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜到歌曲</title>
<link rel="stylesheet" href="all.css" type="text/css" />
</head>
<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.2.min.js "></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
			url:'<%=session.getAttribute("songurl")%>',
			dataType:'jsonp',
			Method:'get',
			success:function(data){
				for (var i=1; i<11; i++){
					document.getElementById('table').rows[i].childNodes[1].innerText = data.song[i-1].songname;
					document.getElementById('table').rows[i].childNodes[2].innerText = data.song[i-1].artistname;
					document.getElementById('table').rows[i].childNodes[3].innerText = data.song[i-1].weight;
				  }
				<%for (int c = 0; c < 10; c++) {%>
				 $("#song<%=String.valueOf(c)%>").click(function(){
					 $.ajax({
							url:'http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid='+ data.song[<%=String.valueOf(c)%>].songid,
							dataType:'jsonp',
							Method:'get',
							success:function(ret){
								console.log(ret);
								//audio = document.createElement("audio");
								//if (audio != null && audio.canPlayType && audio.canPlayType("audio/mpeg"))
								//{
								//audio.src = ret.bitrate.file_link;
								//audio.play();
								$("#playaudio").css("display","block");
								var playsong =document.getElementById("playsong");
								playsong.src= ret.bitrate.show_link;
								var songpic =document.getElementById("songpic");
								songpic.src= ret.songinfo.pic_premium;
								
								<%-- $.ajax({
									url:'http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.lry&songid='+ data.song[<%=String.valueOf(c)%>].songid,
									dataType:'json',
									Method:'get',
									success:function(dat	a){
										console.log(data);
										var lyr=JSON.parse(data);
										console.log(lyr);
										var lyricArr=[];
										//!!一般用来将后面的表达式强制转换为布尔类型的数据（boolean）
										if(!!lyr.lyric){
											$('#showlrc').empty();
											var line=lyr.lyric.split('\n');
											var timeReg=/\[\d{2}:\d{2}.\d{2}\]/g;//时间正则
											var result=[];
											//result是一个时间（秒）+歌词数组
											if(line!=""){
												for(var i in line){
													var time=line[i].match(timeReg);//每组匹配时间，得到时间数组
													if(!time) continue;
													var value=line[i].replace(timeReg,"");//纯歌词
													console.log(time);
													for(var j in time){
														var t=time[j].slice(1,-1).split(':');
														var timeArr=parseInt(t[0],10)*60+parseFloat(t[1]);
														result.push([timeArr,value]);
													}
												}
											}
											//时间排序
											result.sort(function(a,b){
												return a[0]-b[0];
											});
											lyricArr=result;
											renderLyric();//渲染歌词
										}
									}
								}); --%>
								
								 $("#stop").click(function(){
									 $("#playaudio").css("display","none");
									 playsong.pause();
									// audio.pause();
									});
								 $("#songlrc").click(function(){
									 window.location.href=ret.songinfo.lrclink;
									// audio.pause();
									});
								//}
							}
						});
 				
					});
				<%}%>
			}
	  });  
	});
/* function renderLyric(){
	var lyrLine="";
	for(var i=0;i<lyricArr.length;i++){
		lyrLine += "<li data-time='"+lyricArr[i][0]+"'>"+lyricArr[i][1]+"</li>";
	}
	$('#showlrc').append(lyrLine);
}
 */
</script>
<body>
	<div >
	<h1>搜索结果</h1>
		<div align="center">
		<div id="playaudio" style="display: none;">
		<table cellpadding="10px"><tr>
				<td><img src="" id="songpic" width="250" height="250"></img></td>
				<td><audio src="" autoplay="autoplay" id="playsong" controls="controls"></audio></td>
				<td><a id="songlrc" href=#>下载歌词</a>
				<div id="showlrc"></div></td>
				</tr>
		</table>
		</div>
			<table border=0 width=1000 height=600 id='table'>
				<tr>
					<td></td>
					<td>歌名</td>
					<td>歌手</td>
					<td>大小</td>
					<td><a id="stop" href=#>关闭</a></td>
					<td><a aria-hidden="true"
						href="http://service.weibo.com/share/share.php?title=#_loginLayer_1466697157538">分享到微博</a></td>
				</tr>
				<tr>
					<td>1</td>
					<td></td>
					<td></td>
					<td></td>
					<td><a id="song0" href=#>播放</a></td>
				</tr>
				<tr>
					<td>2</td>
					<td></td>
					<td></td>
					<td></td>
					<td><a id="song1" href=#>播放</a></td>
				</tr>
				<tr>
					<td>3</td>
					<td></td>
					<td></td>
					<td></td>
					<td><a id="song2" href=#>播放</a></td>
				</tr>
				<tr>
					<td>4</td>
					<td></td>
					<td></td>
					<td></td>
					<td><a id="song3" href=#>播放</a></td>
				</tr>
				<tr>
					<td>5</td>
					<td></td>
					<td></td>
					<td></td>
					<td><a id="song4" href=#>播放</a></td>
				</tr>
				<tr>
					<td>6</td>
					<td></td>
					<td></td>
					<td></td>
					<td><a id="song5" href=#>播放</a></td>
				</tr>
				<tr>
					<td>7</td>
					<td></td>
					<td></td>
					<td></td>
					<td><a id="song6" href=#>播放</a></td>
				</tr>
				<tr>
					<td>8</td>
					<td></td>
					<td></td>
					<td></td>
					<td><a id="song7" href=#>播放</a></td>
				</tr>
				<tr>
					<td>9</td>
					<td></td>
					<td></td>
					<td></td>
					<td><a id="song8" href=#>播放</a></td>
				</tr>
				<tr>
					<td>10</td>
					<td></td>
					<td></td>
					<td></td>
					<td><a id="song9" href=#>播放</a></td>
				</tr>
			</table>

		</div>
	</div>
</body>
</html>