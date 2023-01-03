<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Document</title>
  <style>
    span {
        display: inline-block;
        height: 40px;
        padding-left: 50px;
        background-image: url("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile7.uf.tistory.com%2Fimage%2F99B806475DD766A230E5B3");
        background-position: 0px 4px;
        background-repeat: no-repeat;
        font-size: 22px;
        color: #470069;
        line-height: 40px;
        font-weight: 500;
        background-size: 30px 30px;
    	cursor: pointer;
    }

    span.on {
        background-image: url("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile5.uf.tistory.com%2Fimage%2F99FAD43F5DD7669C0470F6");
    }
  </style>
</head>
<body>

<span></span>

<script src='https://code.jquery.com/jquery-3.4.1.min.js'></script>
<script>
  $("span").click(function() {

    $(this).toggleClass('on');

  });
</script>

</body>
</html>