<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel = "stylesheet" href="/css/admin/adminMain.css" type="text/css"/>
<link rel="shortcut icon" href="${url}/images/icon.png" type="image/x-icon">
<link rel="icon" href="${url}/images/icon.png" type="image/x-icon">
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script> <%--ajax 라이브러리 추가--%>
<script src ="/js/admin/adminMember.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function(event){
	document.getElementById('top').style.display = "none";
	document.getElementById('footer-section').style.display = "none";
});
</script>
<!-- 탭메뉴 바꾸기 -->

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>mohel Admin</title>

<!-- Custom fonts for this template -->
<link href="${url}/css/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${url}/css/admin/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="${url}/css/admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">


<script>
    //회원정보 가져오기
    $(function() {

        $(".mId").click(function(){
            var nickname = $(this).text();
            console.log(nickname);
            $.ajax({
                url:"/admin/adminMember2",
                data :{
                	nickname:nickname
                }
                ,success:function(result){
                    $("#no").val(result.no);
                    $("#email").val(result.email);
                    $("#nickname").val(result.nickname);
                    $("#pwd").val(result.pwd);
                    $("#tel").val(result.tel);
                    $("#birthdate").val(result.birthdate);
                    $("#gender").val(result.gender);
                    $("#age").val(result.age);
                    $("#height").val(result.height);
                    $("#weight").val(result.weight);
                    $("#active").val(result.active);
                    $("#verify").val(result.verify);
                    $("#recentvisit").val(result.recentvisit);
                    $("#joindate").val(result.joindate);

                }
            });
        });
    });
</script>
<main id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav  sidebar sidebar-dark accordion" id="accordionSidebar" style="background-color: #CCCCCC;">

            <!-- Sidebar - Brand -->
            <a href="/admin/adminMain"><img width="160px" style="margin-left:30px;margin-top:20px;"src="/img/adminLogo.png" ></a>




            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading" style="color: #313942;">
                관리자
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                   aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"style="color: #313942;"></i>
                    <span style="color: #313942;">관리</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-gray-900 py-2 collapse-inner rounded">
                        
                        <a class="collapse-item" href="${url}/admin/adminMember" style="color:#oc09oa;">회원 관리</a>
                        <a class="collapse-item" href="${url}/admin/adminMyMeal" style="color:#oc09oa;">나만의 식단 관리</a>
                        <a class="collapse-item" href="${url}/admin/adminBoard?category=everymeal" style="color:#oc09oa;">모두의 식단 관리</a>
                        <a class="collapse-item" href="${url}/admin/adminBoard?category=exercise" style="color:#oc09oa;">나만의 운동 관리</a>
                        <a class="collapse-item" href="${url}/admin/adminEveryExercise" style="color:#oc09oa;">모두의 운동 관리</a>
                        <a class="collapse-item" href="${url}/admin/adminBoard?category=free" style="color:#oc09oa;">자유게시판 관리</a>
                        <a class="collapse-item" href="${url}/admin/adminBoard?category=challenge" style="color:#oc09oa;">챌린지게시판 관리</a>
                        <a class="collapse-item" href="${url}/admin/adminBoard?category=ba" style="color:#oc09oa;">비포에프터게시판 관리</a>
                    </div>
                </div>
            </li>

            <!-- 선 나눔 -->
            <hr class="sidebar-divider" style="border: 1px solid #313942">


            <!-- 통계 -->
            <li class="nav-item">
                <a class="nav-link" href="${url}/admin/adminMain">
                    <i class="fas fa-fw fa-chart-area" style="color: #313942;"></i>
                    <span style="color: #313942;">통계</span></a>
            </li>

            <!-- 선 나눔 -->
            <hr class="sidebar-divider" style="border: 1px solid #313942">

            <!-- 사이드 메뉴 회원전용탭 -->
            <div class="sidebar-heading" style="color: #313942;">
                회원 전용
            </div>

            <!-- 회원 전용 메뉴 -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                   aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder" style="color: #313942;"></i>
                    <span style="color: #313942;">회원 메뉴 보기</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-gray-900 py-2 collapse-inner rounded">
                        
                        <a class="collapse-item" href="${url}/" style="color:#oc09oa;">메인 페이지</a>
                        <a class="collapse-item" href="${url}/myFoodMain" style="color:#oc09oa;">나만의 식단</a>
                        <a class="collapse-item" href="${url}/everyFoodList" style="color:#oc09oa;">모두의 식단</a>
                        <a class="collapse-item" href="${url}/exercise/exerciseList?category=운동" style="color:#oc09oa;">나만의 운동</a>
                        <a class="collapse-item" href="${url}/exercise/every_exerciseList?category=운동" style="color:#oc09oa;">모두의 운동</a>
                        <a class="collapse-item" href="${url}/board/boardList?category=free" style="color:#oc09oa;">자유게시판</a>
                        <a class="collapse-item" href="${url}/board/boardList?category=challenge" style="color:#oc09oa;">챌린지게시판</a>
                        <a class="collapse-item" href="${url}/board/boardList?category=ba" style="color:#oc09oa;">비포에프터게시판</a>
                        <div class="collapse-divider"></div>
                        <%--                    <h6 class="collapse-header">Other Pages:</h6>--%>
                        <%--                    <a class="collapse-item" href="404.html">404 Page</a>--%>
                        <%--                    <a class="collapse-item" href="blank.html">Blank Page</a>--%>
                    </div>
                </div>
            </li>



            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block"style="border: 1px solid #313942">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>



                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                 aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                               placeholder="Search for..." aria-label="Search"
                                               aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>




                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li onclick="location.href='/member/logout'" class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">관리자 : ${userInfo.nickname} | 로그아웃</span>
                                <img class="img-profile rounded-circle"
                                     src="${url}/css/admin/img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                 aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="${url}/member/logout">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                   

                    <!-- 데이터 테이블 -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">회원정보 목록</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>회원번호</th>
                                        <th>닉네임</th>
                                        <th>이메일</th>
                                        <th>연락처</th>
                                        <th>성별</th>
                                        <th>나이</th>
                                    </tr>
                                    </thead>
                                    <c:forEach var="Lst" items="${memberList}">
                                        <tr>
                                            <td>${Lst.no}</td>
                                            <td><a data-toggle="modal" href="#memberModal" class="mId">${Lst.nickname}</a></td>
                                            <td>${Lst.email}</td>
                                            <td>${Lst.tel}</td>
                                            <td>${Lst.gender}</td>
                                            <td id="adminJD">${Lst.age}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination" style="margin:auto;width: 500px;">
                                        <li class="page-item"style="margin:auto;margin-right:0;">
                                            <c:if test="${pVO.pageNum==1}">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                            </c:if>
                                            <c:if test="${pVO.pageNum>1}">
                                        <li style="margin:auto;">
                                            <a class="page-link" aria-label="Previous" href="${url}/admin/adminMember?pageNum=${pVO.pageNum-1}<c:if test='${pVO.searchWord != null}'>&searchKey=${pVO.searchKey }&searchWord=${pVO.searchWord }</c:if>">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        </c:if>
                                        </li>
                                        <!--  페이지 번호                 1,5      6,10         11,15-->
                                        <c:forEach var="p" begin="${pVO.startPage}"
                                                   end="${pVO.startPage+pVO.onePageCount-1}">
                                            <!--  총 페이지수보다 출력할 페이지번호가 작을때 -->
                                            <c:if test="${p <= pVO.totalPage}">
                                                <c:if test="${p == pVO.pageNum}">
                                                    <li class="page-item">
                                                </c:if>
                                                <c:if test="${p != pVO.pageNum}">
                                                    <li class="page-item">
                                                </c:if>
                                                <a class="page-link" href="${url}/admin/adminMember?pageNum=${p}<c:if test='${pVO.searchWord != null}'>&searchKey=${pVO.searchKey }&searchWord=${pVO.searchWord }</c:if>">
                                                        ${p}
                                                </a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${pVO.pageNum==pVO.totalPage}">
                                            <li class="page-item" style="margin:auto;margin-left:0;">
                                                <a class="page-link" aria-label="Next" href="#">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                        <c:if test="${pVO.pageNum<pVO.totalPage}">
                                            <li class="page-item" style="margin:auto;margin-left:0;">
                                                <a class="page-link" aria-label="Next" href="${url}/admin/adminMember?pageNum=${pVO.pageNum+1}<c:if test='${pVO.searchWord != null}'>&searchKey=${pVO.searchKey }&searchWord=${pVO.searchWord }</c:if>">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>


                                <!-- Modal -->
                                <div class="modal fade" id="memberModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg   " role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">회원 상세정보</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body" style="width: 600px; height: 770px; left: 80px; top: 40px; text-align: center;">
                                                <form name="form1" method="post" action="/admin/adminMemberEdit" style="position: relative; left: 60px;">

                                                        <table id="userInfo${status.count}" border="1">
                                                            <tr>
                                                                <td>회원번호</td>
                                                                <td><input style="margin-bottom: 10px;" name="no" id="no" value="${av.no}" readonly="readonly"></td>
                                                            </tr>
                                                            <tr>
                                                                <th>이메일</th>
                                                                <td><input style="margin-bottom: 10px;" name="email" id="email" value="${av.email}"></td>
                                                            </tr>
                                                            <tr>
                                                                <th>닉네임</th>
                                                                <td><input style="margin-bottom: 10px;" type="text" id="nickname" name="nickname" value="${av.nickname}"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>비밀번호</td>
                                                                <td><input style="margin-bottom: 10px;" name="pwd" id="pwd" value="${av.pwd}">
                                                                <div id="pwdCkMsg" class="ck-msg"></div></td>
                                                            </tr>
                                                            <tr>
                                                                <td>연락처</td>
                                                                <td><input style="margin-bottom: 10px;" name="tel" id="tel" value="${av.tel}" ></td>
                                                            </tr>
                                                            <tr>
                                                                <td>생년월일</td>
                                                                <td><input style="margin-bottom: 10px;" type="text" name="birthdate" id="birthdate" readonly="readonly" value="${av.birthdate}"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>성별</td>
                                                                <td><input style="margin-bottom: 10px; width: 251px" type="text" name="gender" id="gender" value="${av.gender}" readonly="readonly"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>연령</td>
                                                                <td><input style="margin-bottom: 10px; width: 251px" id="age" name="age" value="${av.age}"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>키</td>
                                                                <td><input style="margin-bottom: 10px; width: 251px" id="height" name="height" value="${av.height}"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>몸무게</td>
                                                                <td><input style="margin-bottom: 10px; width: 251px"id="weight" name="weight">${av.weight}</td>
                                                            </tr>
                                                            <tr>
                                                                <td>활동징후</td>
                                                                <td><input type="hidden" id="dbActive" value= ${av.active} >
                                                                	<select style="margin-bottom: 10px; width: 251px" id="active" name="active">
                                                                		<option value = "0.2">거의 운동을 하지 않음</option>
                                                                		<option value = "0.375">가벼운 운동(주 1~3일)</option>
                                                                		<option  value  = "0.55">보통(주 3~5일)</option>
                                                                		<option  value  = "0.725">적극적으로 운동(주 5~7일)</option>
                                                                		<option  value  = "0.9">매우 적극적으로 운동(매일)</option>
                                                                	</select>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>회원구분 : 0-일반, 1-관리자, 9-블랙회원</td>
                                                                <td><input style="margin-bottom: 10px; width: 251px" type="text" id="verify" name="verify" value="${av.verify}"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>가입일시</td>
                                                                <td><input style="margin-bottom: 10px; width: 251px" type="text" id="joindate" name="joindate" value="${av.joindate}" readonly="readonly"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>최근접속일</td>
                                                                <td><input style="margin-bottom: 10px; width: 251px" type="text" id="recentvisit" name="recentvisit" value="${av.recentvisit}" readonly="readonly"></td>
                                                            </tr>
                                                        </table>
                                                        <div class="modal-footer" style="position: relative; right: 25px; width: 400px; top: 32px; ">
                                                            <button type="button" onclick="adminMemberDelete()" class="btn btn-danger">삭제하기</button>
                                                            <button type="submit"  class="btn btn-primary">수정하기</button>
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
                                                        </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->

            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="${url}/member/logout">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="${url}/css/admin/vendor/jquery/jquery.min.js"></script>
    <script src="${url}/css/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${url}/css/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${url}/js/admin/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="${url}/css/admin/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="${url}/css/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${url}/js/admin/js/demo/datatables-demo.js"></script>

</main>
