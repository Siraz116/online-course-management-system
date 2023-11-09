<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Course Management System</title>
    <link rel="stylesheet" href="./public/home.css">
    <!---we had linked our css file----->
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>" >
    <div class="full-page">
        <div class="navbar">
           <div>
                <a 
                	href=''>Welcome to Online Course Management
                </a>
            </div>
        
            <nav>
                <ul id='MenuItems'>
                   <li><a href=''>Home</a></li>
                  <!--    <li><a href='admin.html'>About Us</a></li> -->
                    <li><a href='student.html'>Services</a></li>
                   <!--  <li><a href='#'>Contact</a></li> -->
                    <li><button class='loginbtn' onclick="document.getElementById('login-form').style.display='block'" style="width:auto;">Login</button></li>
                </ul>
            </nav>
        </div>
        <div id='login-form'class='login-page'>
            <div class="form-box">
                <div class='button-box'>
                    <div id='btn'></div>
                    <button type='button'onclick='login()'class='toggle-btn'>Log In</button>
                    <button type='button'onclick='register()'class='toggle-btn'>Register</button>
                </div>
                <form id='login' class='input-group-login' method="post" action="onlineCourse">
                    <label for="classes">Choose a Class:</label>
                    <select name="role" id="classes">
                      <option value="Student">Student</option>
                      <option value="Teacher">Teacher</option>
                      <option value="Admin">Admin</option>
                    </select>
		                    <br><br>
		            <input name="email" type='text'class='input-field'placeholder='Email Id(abc@student.sust.edu)' required >
				    <input name="password" type='password'class='input-field'placeholder='Enter Password' required>
				    <input type='checkbox'class='check-box'><span>Remember Password</span>
				    <button type='submit'class='submit-btn' onclick="document.getElementById('login-form').style.display='block'" style="width:auto;">Log in</button>
				 </form>
		 <form id='register' class='input-group-register' method='post' action='registration'>
            <label for="classes">Choose a Class:</label>
                    <select name="role" id="classes">
                      <option value="Student">Student</option>
                      <option value="Teacher">Teacher</option>
                      <option value="Admin" disabled>Admin</option> 
                    </select>
                    <br><br>
		     <input name='userName' type='text'class='input-field'placeholder='Username' required>
		     <input name='registraion' type='text'class='input-field' placeholder='Registraion No. ' required>
		     <input name='email' type='email'class='input-field'placeholder='Email Id(abc@student.sust.edu)' required>
		     <input name='password' type='password'class='input-field'placeholder='Enter Password' required>
		     <input type='password'class='input-field'placeholder='Confirm Password'  required>
		     <input type='checkbox'class='check-box' required><span>I agree to the terms and                                                   conditions</span>
                    <button type='submit'class='submit-btn'>Register</button>
	         </form>
            </div>
        </div>
    </div>
    <script src="vendor/jquery/jquery.min.css"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
    <script>
    
        var x=document.getElementById('login');
		var y=document.getElementById('register');
		var z=document.getElementById('btn');
		function register()
		{
			x.style.left='-400px';
			y.style.left='50px';
			z.style.left='110px';
		}
		function login()
		{
			x.style.left='50px';
			y.style.left='450px';
			z.style.left='0px';
		}
	</script>
	<script>
        var modal = document.getElementById('login-form');
        window.onclick = function(event) 
        {
            if (event.target == modal) 
            {
                modal.style.display = "none";
            }
        }
    </script>
    <script type="text/javascript">
    	var status=document.getElementById("status").value;
    	if(status=="success"){
    		swal("Congrats","Account Created Successfully", "success");
    	}
    	else if(status=="failed"){
    		swal("Sorry","Wrong Username or Password", "error");
    	}
    
    </script>
</body>
</html>