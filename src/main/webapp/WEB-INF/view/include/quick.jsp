<script type="text/javascript">
	$(function(){
		 var h = ($(window).height()-$(".divPop").height())/2+$(window).scrollTop();
         var w = ($(window).width()-$(".divPop").width())/2+$(window).scrollLeft();
         $(".divPop").css({
             "top":h+"px",
             "left":w+"px"
         });
	}
</script>
<body>
		
	<div class="quickMenu">
        <div><img src="/pro/img/quick_01.jpg"></div>
        <div><img src="/pro/img/quick_02.jpg"></div>
        <div><img src="/pro/img/quick_03.jpg"></div>
        <div><img src="/pro/img/quick_04.jpg"></div>
            <div id="goTop"><img src="/pro/img/quick_05.jpg"></div>
     </div>
</body>
</html>