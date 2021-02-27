const loginUser = () => {

    let email = document.getElementById('lgEmail').value;
    let password = document.getElementById('lgPassword').value;


    let userLogin = {

        email:email,
        password:password
    }

    axios({
        url:"http://localhost:8080/api/auth/login",
        method: "POST",
        data:userLogin

    })  
     .then((resp) => {
        console.log({ resp });
        // Clear information
        document.getElementById("lgEmail").value = "";
        document.getElementById("lgPassword").value = "";
  
        // Store token into localStorage
        localStorage.setItem("USER_TOKEN", resp.data);
  
        document.location.href = "../../Elearning/index.html";
      })
      .catch((error) => {
        console.log({...error});
      });
  };

