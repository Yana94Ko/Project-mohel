<style>
#profileImg {
	border-radius: 100%;
	width: 100px;
	height: 100px;
}
</style>
<script type="text/javascript">
	$(function() {
		const fileInput = document.getElementById('profile');
		const handleFiles = (e) => {
			const selectedFile = [...fileInput.files];
			const fileReader = new FileReader();
			
			fileReader.readAsDataURL(selectedFile[0]);
			fileReader.onload = function() {
				document.getElementById("profileImg").src = fileReader.result;
			};
		};
		fileInput.addEventListener("change", handleFiles);
	});
</script>
<div class="container">
	<form method="post" action="/member/signupOk" enctype="multipart/form-data">
		<input id="profile" type="file" accept="image/jpg, image/jpeg, image/png" multiple="multiple" hidden>
		<label for="profile"><img id="profileImg" src="/img/member/defaultProfile.png"></label>
	</form>
</div>