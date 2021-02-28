// Get id from URL
let url = new URL(document.location.href);
let id = url.searchParams.get("id");

const loadData = () => {
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  // Call api to get role
  axios({
    url: `http://localhost:8080/api/admin/category/${id}`,
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log(resp);
      let category = resp.data;
      // Set value for UI element
      document.getElementById("title").value = category.title;
      document.getElementById("icon").value = category.icon;
    })
    .catch((error) => {
      console.log({ error });
    });
};
loadData();

const updateCategory = () => {
  // Get information in form
  let title = document.getElementById("title").value;
  let icon = document.getElementById("icon").value;
  // Create updateRole model
  let categoryModel = {
    id: id,
    title: title,
    icon: icon,
  };
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  // Call api to get category
  axios({
    url: `http://localhost:8080/api/admin/category/${id}`,
    method: "PUT",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    data: categoryModel,
  })
    .then((resp) => {
      console.log(resp);
      swal("Thông báo!", "Sửa đổi thành công", "success").then(() => {
        document.location.href = "../../../admin/category/category-index.html";
      });
    })
    .catch((error) => {
      console.log(error);
      swal("Thông báo!", "Sửa đổi thất bại", "error");
    });
};
