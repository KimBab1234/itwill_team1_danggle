<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출고 예정 입력</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<form action="">
		<table>
			<tr>
				<th>일자</th>
				<td><input type="date" name="schDate"></td>
				<th>유형</th>
			</tr>
			<tr>
				<th>담당자</th>
				<td>
					<input type="text" width="50">
					<button>Q</button>
					<input type="text">
				</td>
				<th>납기일자</th>
				<td><input type="date" name="deliDate"></td>
			</tr>
			<tr>
				<th>비고</th>
				<td><input type="text"></td>
			</tr>
		</table>
	</form>
	
	<!-- ------------------------------------------------------------ -->
	
	<table>
		<tr height="10px">
			<th><input type="checkbox"></th>
			<th><i style='font-size:15px; color: #000080;' class='fas'>&#xf0ab;</i></th>
			<th>품목코드</th>
			<th>품목명</th>
			<th>규격</th>
			<th>수량</th>
			<th>납기일자</th>
			<th>적요</th>
			<th>출고처리</th>
		</tr>
		<tr height="10px">
			<td><input type="checkbox" placeholder="1"></td>
			<td><i style='font-size:15px; color: #000080;' class='fas'>&#xf00e;</i></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>====/==/==</td>
			<td></td>
			<td><a href="#">재고번호</a></td>
		</tr>
		<tr height="10px">
			<td><input type="checkbox" placeholder="1"></td>
			<td><i style='font-size:15px; color: #000080;' class='fas'>&#xf055;</i></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>====/==/==</td>
			<td></td>
			<td><a href="#">재고번호</a></td>
		</tr>
		<tr height="10px">
			<td><input type="checkbox" placeholder="1"></td>
			<td><i style='font-size:15px; color: #000080;' class='fas'>&#xf067;</i></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>====/==/==</td>
			<td></td>
			<td><a href="#">재고번호</a></td>
		</tr>
		<tr height="10px">
			<td><input type="checkbox" placeholder="1"></td>
			<td><i style='font-size:15px; color: #000080;' class='fas'>&#xf0fe;</i></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>====/==/==</td>
			<td></td>
			<td><a href="#">재고번호</a></td>
		</tr>
	</table>

</body>
</html>