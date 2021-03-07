const login = () => {
  // Get information from form
  let email = document.getElementById("lgEmail").value;
  let password = document.getElementById("lgPassword").value;
  // Create loginModel object
  let loginModel = {
    email: email,
    password: password,
  };
  // Call api to get access token
  axios({
    url: "http://localhost:8080/api/admin/auth/login",
    method: "POST",
    data: loginModel,
  })
    .then((resp) => {
      console.log({ resp });
      // Clear information
      document.getElementById("lgEmail").value = "";
      document.getElementById("lgPassword").value = "";

      // Store token into localStorage
      localStorage.setItem("USER_TOKEN", resp.data);

      document.location.href = "../user/index.html";
    })
    .catch((error) => {
      console.log({ ...error });
    });
};

function checkAuth() {
  let token = localStorage.getItem("USER_TOKEN");
  if (token != null) {
    document.location.href = "../role/role-index.html";
  }
}

checkAuth();
