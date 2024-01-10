<%-- 
    Document   : login
    Created on : Oct 8, 2023, 9:58:29 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Bootstrap Simple Login Form</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/login.css"/>
<!--<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="134212668271-bg6hmdtmakjsrvr7ribbmg6jcnqiq2ka.apps.googleusercontent.com">-->
<script>

  function statusChangeCallback(response) {  // Called with the results from FB.getLoginStatus().
    console.log('statusChangeCallback');
    console.log(response);                   // The current login status of the person.
    if (response.status === 'connected') {   // Logged into your webpage and Facebook.
      testAPI();  
    } else {                                 // Not logged into your webpage or we are unable to tell.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this webpage.';
    }
  } 


  function checkLoginState() {               // Called when a person is finished with the Login Button.
    FB.getLoginStatus(function(response) {   // See the onlogin handler
      statusChangeCallback(response);
    });
  }


  window.fbAsyncInit = function() {
    FB.init({
      appId      : '727708609310822',
      cookie     : true,                     // Enable cookies to allow the server to access the session.
      xfbml      : true,                     // Parse social plugins on this webpage.
      version    : 'v3.2'           // Use this Graph API version for this call.
    });


    FB.getLoginStatus(function(response) {   // Called after the JS SDK has been initialized.
      statusChangeCallback(response);        // Returns the login status.
    });
  };
 
  function testAPI() {                      // Testing Graph API after login.  See statusChangeCallback() for when this call is made.
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me?fields=email,name', function(response) {
      console.log(response);
      window.location.href='Login?action=Face&name=' + response.name + '&email=' + response.email+'&id=' +response.id;
    });
  }
    function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
  console.log('Name: ' + profile.getName());
  console.log('Image URL: ' + profile.getImageUrl());
  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
  
  var id_token = googleUser.getAuyjResponse().id_token;
  console.log('ID Token: ' + id_token);
}
</script>
</head>
<body>
<div class="login-form">
    <form action="login" method="post">
        <h2 class="text-center">Log in</h2>       
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Username" required="required" name="username">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Password" required="required" name="pass">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Log in</button>
            <p style="color: red">${requestScope.ms}</p>
        </div>
        
        <div class="form-group">
<!--           <div class="g-signin2" data-onsuccess="onSignIn"></div>-->
            <button type="submit" class=" btn-block"><a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:9999/assignment/LoginGoogleHandler&response_type=code
		   &client_id=134212668271-0c5i2mfd8ca2n0supe329jps5fo1e5sk.apps.googleusercontent.com&approval_prompt=force">Login with Google</a></button>
        </div>
        <div class="form-group">    
            <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
            </fb:login-button>

<!--            <div id="status">
            </div>-->

        <!-- Load the JS SDK asynchronously -->
        <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js"></script>
            
        </div>
        <div class="clearfix">
            <label class="float-left form-check-label"><input type="checkbox"> Remember me</label>
            <a href="forgotPassword.jsp" class="float-right">Forgot Password?</a>
        </div>        
    </form>
    <p class="text-center"><a href="signin">Create an Account</a></p>
    <p class="text-center" style="color: red;">${status}</p>
</div>
</body>
</html>
