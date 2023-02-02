<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고 예정 입력</title>
</head>
<body>
	<form action="">
		<table>
			<tr>
				<th>일자</th>
				<td><input type="date"></td>
				<th>유형</th>
				<td>
					<select name="">
						<option>발주서</option>
						<option>구매</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>담당자</th>
				<td>
					<input type="text" width="50">
					<button>Q</button>
					<input type="text">
				</td>
				<th>납기일자</th>
				<td><input type="date"></td>
			</tr>
			<tr>
				<th>비고</th>
				<td><input type="text"></td>
			</tr>
		</table>
	</form>
	

</body>
</html>