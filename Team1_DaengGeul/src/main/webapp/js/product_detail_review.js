$(function() {
	//--------------------- 리뷰 상세 게시판 ---------------------
	$(".reviewSubject").on("click", function() {
		var row = $(this);
		var reviewIdxNum = $(this).children('input').attr("id");
		var viewNum = Number($("#"+reviewIdxNum).val());
		if(viewNum>0) {
			var detail = $("#"+reviewIdxNum).parents('tr').next();
			if(detail.is(":visible")) {
				detail.css("display", "none")
			}else {
				detail.css("display", "table-row")
			}
		} else {
				$("#"+reviewIdxNum).val(viewNum+1);
				$.ajax({
					type: "post",
					url: "ReviewDetail.re",
					dataType: "text", 
					data: {
						review_idx: reviewIdxNum,
						product_idx: '${product.product_idx}'
					},
					success: function(response) {
						row.parent('tr').after("<tr style='display: table-row;'><td colspan='6' style='vertical-align: middle;'>"+response+"</td></tr>");
					},
					error: function(xhr, textStatus, errorThrown) { 
						alert("리뷰 상세조회 실패!");
					}
				});
		}

	});
	
	
	var reviewLikeDone = '${review.review_like_done}';
	if(reviewLikeDone =='Y') {
		$(".review_like1").css("color", "blue");
		$(".review_like").val(reviewLikeDone);
	} else {
		$(".review_like1").css("color", "gray");
		$(".review_like").val(reviewLikeDone);
	}
	
	
	
});
	$("button").on("click", function() {
		var likeRow = $(this).attr("id");
		var nowLike = $(this).val();
		alert($(this).attr("id"));
		var id = '${sessionScope.sId}';
		if(id=='') {
			alert("로그인 후 이용하세요.");
		} else {
// 			$.ajax({
// 				type: "post",
// 				url: "ReviewLikeUpdate.re",
// 				data: {
// 					review_idx: '${param.review_idx}',
// 					review_like_done: nowLike
// 					},
// 					success: function(response) {
// 						likeRow.children(".like_count").text(response);
// 						if(nowLike =='Y') {
// 							likeRow.children(".review_like1").css("color", "gray");
// 							likeRow.val("N");
// 						} else {
// 							likeRow.children(".review_like1").css("color", "blue");
// 							likeRow.val("Y");
// 						}
						
// 					},
// 					error: function(xhr, textStatus, errorThrown) { 
// 						// 요청에 대한 처리 실패 시(= 에러 발생 시) 실행되는 이벤트
// 						$("#resultArea").html("xhr = " + xhr + "<br>textStatus = " + textStatus + "<br>errorThrown = " + errorThrown);
// 					}
// 			});
		}
	});

