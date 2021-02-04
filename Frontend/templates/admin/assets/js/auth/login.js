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
    })
    .catch((error) => {
      console.log(err.response.data);
    });
};
