
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset==UTF-8">
<title>云音乐</title>
<meta charset="utf-8">
<meta name="referrer" content="no-referrer">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="index.css">
</head>
<body>
	<div class="container">
		<div class="top">
			<div class="music-lyrics">
				<div class="lyric-view">
					<ul class="lyric"></ul>
				</div>
			</div>
		</div>
		<div class="bottom">
			<div class="content">
				<audio src=""></audio>
				<!--歌曲信息-->
				<div class="music-message">
					<p class="musicname"></p>
					<p class="singer"></p>
					<!--频道-->
					<p class="record"></p>
				</div>
				<!--分享，收藏，喜欢按钮-->
				<div class="music-fan">
					<a class="glyphicon glyphicon-share" aria-hidden="true"
						href="http://service.weibo.com/share/share.php?title=#_loginLayer_1466697157538"
						target="_blank"></a> <span
						class="glyphicon glyphicon-star colored"></span> <span
						class="glyphicon glyphicon-heart colored"></span>
				</div>
			</div>
			<!--进度条-->
			<div class="basebar">
				<div class="progressbar"></div>
			</div>
			<div class="controler">
				<div class="play-control">
					<span class="btn1 glyphicon glyphicon-play" title="播放/暂停"></span> <span
						class="glyphicon glyphicon-step-forward btn2" title="换频道"></span>
					<span class="glyphicon glyphicon-forward btn3" title="下一曲"></span>
				</div>
				<div class="music-control">
					<div class="volumnbar">
						<div class="volumn"></div>
					</div>
					<!--<span class="glyphicon glyphicon-volume-down" title="音量"></span>-->
					<span class="glyphicon glyphicon-repeat colored" title="循环"></span>
					<span class="glyphicon glyphicon-align-justify colored lyriced"
						title="歌词"></span>
				</div>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
	<script src="music.js"></script>
</body>
</html>