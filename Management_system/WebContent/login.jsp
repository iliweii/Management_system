<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登录 | 易管 信息管理系统</title>

<!-- link bootstrap4 css -->
<link rel="stylesheet" href="./css/bootstrap.min.css">
<!-- link style css -->
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/login.main.css">

<!-- script bootstrap4 js -->
<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/popper.min.js"></script>
<!-- script sweetalert js -->
<script src="./js/sweetalert.min.js"></script>
<!-- script GVerify js -->
<script src="./js/gVerify.js"></script>
<!-- script login js -->
<script src="./js/login.main.js"></script>

</head>
<body>

	<!-- body start -->
	<section class="body">
		<div class="inner">

			<!-- 主体部分 登录选项组 -->
			<div class="main">
				<a class="link link--kukuri" href="#" data-letters="易管">易管</a>
				<div class="maintitle">信息管理系统</div>
				<div class="opgroup">
					<div>
						<div class="opbtn" id="StudentBtn">
							<svg t="1594186054599" class="icon" viewBox="0 0 1024 1024"
								version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1140"
								width="40" height="40">
                                <path
									d="M513.4 952.6l275.3-2.3s-13.8-220.8-202.5-255.2c-51.4-4.3-74-2.6-74-2.6v0.3c0.1 161 1.2 259.8 1.2 259.8z"
									fill="#FFFFFF" p-id="1141"></path>
                                <path
									d="M510 952.6l-274.2-2.3s13.7-221 201.7-255.4c51.2-4.3 73.6-2.6 73.6-2.6v0.3c0 161.2-1.1 260-1.1 260z"
									fill="#E1E6F7" p-id="1142"></path>
                                <path
									d="M408 599.1h192.6v149.4s-47.5 74.9-95.7 74.9c-48.1 0-96.9-74.9-96.9-74.9V599.1z"
									fill="#FFDDB8" p-id="1143"></path>
                                <path d="M408 731.5V582.1h192.6v81.5"
									fill="#E5C6A5" p-id="1144"></path>
                                <path
									d="M410 646.7c17.2 29 4.2 71.5 20.6 96.1 16.5 24.6 49.3 42.3 77.2 68.2-36.7 17.1-97.7 5.7-124.7-39.7-27-45.4-9.8-107.6 26.9-124.6z"
									fill="#C74A4A" p-id="1145"></path>
                                <path
									d="M482.8 780.3h54.4c12.5 0 22.7 10.1 22.7 22.7l-6.8 24.9c0 12.5-16 22.7-24.5 22.7h-37.1c-8.5 0-24.5-10.1-24.5-22.7l-6.8-24.9c-0.1-12.5 10.1-22.7 22.6-22.7z"
									fill="#E25656" p-id="1146"></path>
                                <path
									d="M505.8 823.4c-16.5 3.1-26.9 25.1-32.1 45.3-6.6 25.8-10.3 56.3-13.2 82.6 33.4 0 42.6 0 77.1 0.4 12.1-28.7 14.9-64.6-0.5-98.9-7.6-17.1-17.6-32-31.3-29.4zM603.3 646.7c-17.2 28.9-4.2 71.4-20.7 96-16.5 24.5-49.4 42.2-77.3 68.1 36.8 17 109.1-5.5 136.2-50.8 27-45.4-1.5-96.3-38.2-113.3z"
									fill="#E25656" p-id="1147"></path>
                                <path
									d="M511.9 170.7c-131.1 0-226.3 107.9-226.3 241 0 44.7 11.4 122.4 31.2 158.2 39.1 70.8 164.9 88.9 195.4 90.2 0.1-127.9-0.3-323.3-0.3-489.4z"
									fill="#F5D4B1" p-id="1148"></path>
                                <path
									d="M512.5 170.7c131.1 0 226.4 107.9 226.4 241 0 44.7-11.4 122.4-31.2 158.2-39.2 70.8-165 88.9-195.5 90.2 0-128 0.3-323.3 0.3-489.4z"
									fill="#FFDDB8" p-id="1149"></path>
                                <path
									d="M569.1 66.5l-50.3 54.4s-146.2-2.9-222.1 88.4C226.2 294 266.4 448 281.3 463.1c-1.1-6.8 37 9.2 33.8-2.3-31.8-113.5 83.9-41.4 167.1-61.2 114.6-27.3 184.9-74 198.9-61.2 34.5 38.5 32 138.2 32 138.2s65.5-53.6 59.4-151.8c-4.2-67.4-50.7-135.2-84.5-156.4-33.9-21.2-58.3-31.5-86.8-36.3 6-9.8 9.2-34 9.2-34l-36.6 22.7-4.7-54.3z"
									fill="#333333" p-id="1150"></path>
                                <path
									d="M762.3 405.3c15.3 5.2 19.7 33.2 9.9 62.5-9.9 29.4-30.3 49-45.6 43.8-15.3-5.2-19.7-33.2-9.9-62.5s30.2-48.9 45.6-43.8z"
									fill="#FFDDB8" p-id="1151"></path>
                                <path
									d="M265.7 408.6c-15.3 5.2-19.7 33.6-9.9 63.4 9.9 29.8 30.3 49.6 45.6 44.4 15.3-5.2 19.7-33.6 9.9-63.4-9.9-29.7-30.3-49.6-45.6-44.4z"
									fill="#F5D4B1" p-id="1152"></path>
                                <path
									d="M496.2 823.7c-13.5 3.2-22 25.1-26.2 45.4-5.3 25.7-8.3 55.9-10.7 82.2 19.7 0.1 23.5 0.1 38.2-0.2 22.8-31.3 39.6-64.4 37.4-100.4-1.1-18.4-27.5-29.6-38.7-27z"
									fill="#C74A4A" p-id="1153"></path>
                            </svg>
							我是学生
						</div>
						<div class="opbtn" id="UserBtn">
							<svg t="1594186121238" class="icon" viewBox="0 0 1024 1024"
								version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1422"
								width="40" height="40">
                                <path
									d="M512.5 952.6H786s-13.6-191.3-200.4-221c-50.9-3.7-73.2-2.3-73.2-2.3v0.2c0.1 158.7 0.1 223.1 0.1 223.1z"
									fill="#484848" p-id="1423"></path>
                                <path
									d="M511.4 952.6H239s13.6-191.5 199.5-221.3c50.6-3.7 72.9-2.3 72.9-2.3v223.6z"
									fill="#333333" p-id="1424"></path>
                                <path
									d="M443.7 619.3h145.1v257.2H443.7V619.3z" fill="#FFDDB8"
									p-id="1425"></path>
                                <path
									d="M443.7 614.8h145.1v45.1l-145.1 88V614.8z" fill="#E5C6A5"
									p-id="1426"></path>
                                <path
									d="M512.3 952.4s31.2 0.2 46 0.2c6.1-9.9 77.9-204 77.3-207.1-0.3-1.9-94.2 18.2-94.2 18.2l-29.1-20.3v209z"
									fill="#FFFFFF" p-id="1427"></path>
                                <path
									d="M519.1 952.4s-31.2 0.2-46 0.2c-6.1-9.9-77.9-203.7-77.3-206.8 0.3-1.9 96.4 17.7 96.4 17.7l26.9-16.4v205.3z"
									fill="#E0ECFF" p-id="1428"></path>
                                <path
									d="M592.9 695.4l49.1 40.3s1 114.1 0 109.8c-1-4.3-125.1-98.6-125.1-98.6l76-51.5z"
									fill="#FFFFFF" p-id="1429"></path>
                                <path
									d="M440.8 695.4l-49.3 40.4s-1 114.2 0 109.9c1-4.3 125.6-98.7 125.6-98.7l-76.3-51.6z"
									fill="#FFFFFF" p-id="1430"></path>
                                <path
									d="M512.5 199.9v443.9c60.1-3 132.8-43.5 167.1-108.4 17.6-33.3 19-71.6 19-113.2 0-122.8-79.6-222.3-186.1-222.3z"
									fill="#FFDDB8" p-id="1431"></path>
                                <path
									d="M512.5 199.9v443.9c-60.1-3-132.8-43.5-167.1-108.4-17.6-33.3-19-71.6-19-113.2 0-122.8 79.6-222.3 186.1-222.3z"
									fill="#F5D4B1" p-id="1432"></path>
                                <path
									d="M341 399.4c3.4-50.5 26.8-108.5 105.6-140.7 19 4.3 58.6 37.5 129 4.5 47.9-22.4 108.1 77.3 116.7 118.3 6.9 32.8-6.5 29.4 15.6 22.3 5-3.9 38-54.2-13.4-256.8C590.7 58.7 507 66.6 507 66.6S375.2 80.9 315 182.7c-16.6 90-20.3 158.6-8.4 208.3 11.1-27.6 24.8-16 34.4 8.4z"
									fill="#5C3E31" p-id="1433"></path>
                                <path
									d="M718 369.6c12 4 15.4 25.9 7.7 48.7-7.7 22.9-23.7 38.2-35.6 34.1-12-4-15.4-25.9-7.7-48.7 7.7-22.8 23.7-38.1 35.6-34.1z"
									fill="#FFDDB8" p-id="1434"></path>
                                <path
									d="M309.1 368.6c-12 4.1-15.4 26.2-7.7 49.4s23.7 38.7 35.6 34.6c12-4.1 15.4-26.2 7.7-49.4s-23.6-38.7-35.6-34.6z"
									fill="#F5D4B1" p-id="1435"></path>
                                <path
									d="M513 816.7s80 69.5 80.7 24.2v-78.6s-6.8-21.5-36.7 0c-41.7 28.5-44 30.2-44 30.2v24.2z"
									fill="#484848" p-id="1436"></path>
                                <path
									d="M520.3 816.7s-80 70-80.7 24.4v-79.2s6.8-21.6 36.7 0c41.7 28.7 44 30.4 44 30.4v24.4z"
									fill="#333333" p-id="1437"></path>
                                <path
									d="M312 793v159.6h-75.2s7.3-91.7 75.2-159.6zM787.8 952.6h-75.2V793c67.9 67.9 75.2 159.6 75.2 159.6z"
									fill="#E0ECFF" p-id="1438"></path>
                            </svg>
							我是管理员
						</div>
					</div>
					<div>
						<div class="opbtn" id="TeacherBtn">
							<svg t="1594186102922" class="icon" viewBox="0 0 1024 1024"
								version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1282"
								width="40" height="40">
                                <path
									d="M582.1 750.9l-140.3-0.7V587.3h140.3z" fill="#FFDDB8"
									p-id="1283"></path>
                                <path
									d="M582.1 647.6l-140.3 82.7v-143h140.3z" fill="#EBCCAA"
									p-id="1284"></path>
                                <path
									d="M512.4 203.4c-0.6 0-1.1-0.1-1.7-0.1-103 0-193.3 100.3-193.3 224.1s123.2 224.1 193.3 224.1c0.6 0 1.1 0 1.7-0.1v-448z"
									fill="#F5D4B1" p-id="1285"></path>
                                <path
									d="M512.4 203.4c0.6 0 1.1-0.1 1.7-0.1 103 0 193.3 100.3 193.3 224.1S584.2 651.5 514.1 651.5c-0.6 0-1.1 0-1.7-0.1v-448z"
									fill="#FFDDB8" p-id="1286"></path>
                                <path
									d="M295.8 364.2c-3.3-58.2-6.6-140.3 16.6-191.6 23.3-51.3 146.3-106.1 199.4-106.1s156.2 51.3 179.5 82.1C705 166.7 726 266 724.5 350.5c-1 59.4-10.3 82.3-19.9 99.2-4.8 8.5 6.6-109.5-99.7-201.9-16.6-10.3-32.1 6.4-53.2 10.3-35.3 6.5-66.5 6.8-93.1 6.8-31 0-88.1 39.8-103 78.7-26.4 68.7-19.9 119.8-19.9 119.8s-36.6-41-39.9-99.2z"
									fill="#634335" p-id="1287"></path>
                                <path
									d="M294.585525 430.047842a51.3 25.7 72.645 1 0 49.06001-15.33217 51.3 25.7 72.645 1 0-49.06001 15.33217Z"
									fill="#F5D4B1" p-id="1288"></path>
                                <path
									d="M512.1 720.1l-133.5 24s-85.5 37.6-109.5 75.3c-24 37.6-61.6 133.5-61.6 133.5H512V720.1z"
									fill="#E0ECFF" p-id="1289"></path>
                                <path
									d="M512.4 720.1l133.5 24s85.5 37.6 109.5 75.3c24 37.6 61.6 133.5 61.6 133.5H512.4V720.1z"
									fill="#FFFFFF" p-id="1290"></path>
                                <path
									d="M686.998021 464.501055a25.7 51.3 17.355 1 0 30.604682-97.929125 25.7 51.3 17.355 1 0-30.604682 97.929125Z"
									fill="#FFDDB8" p-id="1291"></path>
                                <path
									d="M495.3 850.1l-56.9-126.6 71.8-3.4 75.3 3.4-58.2 126.6 10.2 102.7h-51.2z"
									fill="#333333" p-id="1292"></path>
                                <path
									d="M432.5 672l78.7 47.9-130.1 102.7V719.9zM589.9 672l-78.7 47.9 130 102.7V719.9z"
									fill="#FFFFFF" p-id="1293"></path>
                            </svg>
							我是老师
						</div>
						<div class="opbtn" id="AdminBtn">
							<svg t="1594186131576" class="icon" viewBox="0 0 1024 1024"
								version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1573"
								width="40" height="40">
                                <path
									d="M582.1 750.9l-140.3-0.7V587.3h140.3z" fill="#FFDDB8"
									p-id="1574"></path>
                                <path
									d="M582.1 647.6l-140.3 82.7v-143h140.3z" fill="#EBCCAA"
									p-id="1575"></path>
                                <path
									d="M512.4 203.4c-0.6 0-1.1-0.1-1.7-0.1-103 0-193.3 100.3-193.3 224.1s123.2 224.1 193.3 224.1c0.6 0 1.1 0 1.7-0.1v-448z"
									fill="#F5D4B1" p-id="1576"></path>
                                <path
									d="M512.4 203.4c0.6 0 1.1-0.1 1.7-0.1 103 0 193.3 100.3 193.3 224.1S584.2 651.5 514.1 651.5c-0.6 0-1.1 0-1.7-0.1v-448z"
									fill="#FFDDB8" p-id="1577"></path>
                                <path
									d="M295.8 364.2c-3.3-58.2-6.6-140.3 16.6-191.6 23.3-51.3 146.3-106.1 199.4-106.1s156.2 51.3 179.5 82.1C705 166.7 726 266 724.5 350.5c-1 59.4-10.3 82.3-19.9 99.2-4.8 8.5 6.6-109.5-99.7-201.9-16.6-10.3-32.1 6.4-53.2 10.3-35.3 6.5-66.5 6.8-93.1 6.8-31 0-88.1 39.8-103 78.7-26.4 68.7-19.9 119.8-19.9 119.8s-36.6-41-39.9-99.2z"
									fill="#634335" p-id="1578"></path>
                                <path
									d="M294.585525 430.047842a51.3 25.7 72.645 1 0 49.06001-15.33217 51.3 25.7 72.645 1 0-49.06001 15.33217Z"
									fill="#F5D4B1" p-id="1579"></path>
                                <path
									d="M512.1 720.1l-133.5 24s-85.5 37.6-109.5 75.3c-24 37.6-61.6 133.5-61.6 133.5H512V720.1z"
									fill="#333333" p-id="1580"></path>
                                <path
									d="M512.4 720.1l133.5 24s85.5 37.6 109.5 75.3c24 37.6 61.6 133.5 61.6 133.5H512.4V720.1z"
									fill="#555555" p-id="1581"></path>
                                <path
									d="M686.998021 464.501055a25.7 51.3 17.355 1 0 30.604682-97.929125 25.7 51.3 17.355 1 0-30.604682 97.929125Z"
									fill="#FFDDB8" p-id="1582"></path>
                                <path
									d="M632.9 735.3L565 952.6h-50.9V720.1z" fill="#FFFFFF"
									p-id="1583"></path>
                                <path
									d="M391.9 735.3l67.9 217.3h50.9V720.1z" fill="#E0ECFF"
									p-id="1584"></path>
                                <path
									d="M495.3 850.1l-56.9-126.6 71.8-3.4 75.3 3.4-58.2 126.6 10.2 102.7h-51.2z"
									fill="#7FB2FF" p-id="1585"></path>
                                <path
									d="M432.5 672l78.7 47.9-130.1 102.7V719.9zM589.9 672l-78.7 47.9 130 102.7V719.9z"
									fill="#FFFFFF" p-id="1586"></path>
                            </svg>
							高级管理员
						</div>
					</div>
				</div>
			</div>

			<!-- 主体部分：输入组 -->
			<div class="form border border-success rounded shadow px-5 py-5" id="Form">

				<!-- 管理员登录名 -->
				<div class="col-md-12 mb-3">
					<label for="Tbuser">管理员登录名</label> <input type="text"
						class="form-control" id="Tbuser" required>
					<div id="tbuserFeedback" class="valid-feedback"></div>
				</div>

				<!-- 管理员密 码 -->
				<div class="col-md-12 mb-3">
					<label for="Tbpwd">管理员密码</label> <input type="password"
						class="form-control" id="Tbpwd" required>
					<div id="tbpwdFeedback" class="invalid-feedback"></div>
				</div>

				<!-- 验证码 -->
				<div class="mb-3" style="display: flex;">
					<div class="col-md-5">
						<label for="Tbverify">验证码</label> <input type="text"
							class="form-control" id="Tbverify" required>
						<div id="tbverifyFeedback" class="invalid-feedback"></div>
					</div>
					<div class="col-md-2"></div>
					<div id="verify-img" class="col-md-4"></div>
				</div>

				<!-- 提交按钮 和 重置按钮 -->
				<div class="col-md-12 mb-3">
					<input id="Loginbtn" class="btn btn-primary mt-1" type="submit"
						value="登录"> 
					<input id="Resetbtn"
						class="btn btn-secondary mt-1 mx-2" type="reset" value="重置">
					<input id="ChangeIdbtn" class="btn btn-warning mt-1 mx-2" type="button" value="更换身份">
				</div>

			</div>

		</div>
	</section>

	<!-- footer start -->
	<%@ include file="foot.jsp"%>

</body>
</html>