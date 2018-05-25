<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Title</title>
	<link rel="stylesheet" href="/css/core.css" type="text/css">
	<script type="text/javascript" src="/js/common.js"></script>
</head>
<body>

<div class="container">
	<div class="row top-margin-5">
		<div class="col">
			<header class="text-align-center">
				<h1>
					<a class="a-no-decoration text-dark" href="/index.php">nQuirer</a>
				</h1>
			</header>
		</div>
	</div>
	
	<div class="row align-items-center">
		<div class="col">
			<header class="text-align-center">
				<h2>Sign up</h2>
			</header>
		</div>
	</div>
	
	<div class="row align-items-center">
		<div class="col-6 offset-3">
			<form id="sign-up-form" action="#" method="post">
				<div class="form-group">
					<label for="email">Email address</label>
					<input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Email">
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" class="form-control" id="password" placeholder="Password">
				</div>
				<a href="/signin.php"><small class="form-text text-muted">Or sign in with existing account</small></a>
				<button type="submit" class="btn btn-primary top-margin-5">Sign up</button>
			</form>
		</div>
	</div>
</div>


<script type="text/javascript" src="js/jquery-3.3.1.js" defer></script>
<script type="text/javascript" src="js/sign.js" defer></script>
</body>
</html>