const addCategory = () => {
  // Get information in form
  let title = document.getElementById("title").value;
  let icon = document.getElementById("icon").value;
  // Create information object
  let role = {
    title: title,
    icon: icon,
  };
  // Call api to create category
  // Get token from local storage
  let token = localStorage.getItem("USER_TOKEN");
  axios({
    url: "http://localhost:8080/api/admin/category",
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    data: role,
  })
    .then((resp) => {
      console.log(resp);
      swal("Thông báo!", "Thêm mới thành công!", "success").then((value) => {
        window.location.href = "../../../../admin/category/category-index.html";
      });
    })
    .catch((error) => {
      console.log(error);
      swal("Thông báo!", "Thêm mới thất bại!", "error");
    });
};
