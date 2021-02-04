const loadCategories = () => {
  // Reference category list from DOM
  let tbodyCategories = document.getElementById("tbodyCategories");
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  // Retrieve categories by calling api
  axios({
    url: "http://localhost:8080/api/admin/category",
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      // Create content
      let content = "";
      // Get categories from response body
      let categories = resp.data;
      let i = 1; // index variable
      for (const category of categories) {
        content += `<tr>
      <th>${i}</th>
      <td>${category.title}</td>
      <td><h4><i class="${category.icon}"></i></h4></td>
      <td>
        <a
            class="btn btn-sm btn-info btn-round py-1 font-weight-bold"
            href="category-edit.html?id=${category.id}">Sửa</a>
        <a
            class="btn btn-sm btn-danger btn-round py-1 font-weight-bold"
            href="javascript:void(0)"
            onclick="deleteCategory(${category.id})">Xóa</a>
      </td>
      </tr>`;
        i++;
      }
      tbodyCategories.innerHTML = content;
    })
    .catch((error) => {
      console.log({ error });
    });
};
loadCategories();

const deleteCategory = (id) => {
  // Show alert to confirm deletion action
  swal("Bạn có muốn xóa hay không?", { button: true }).then((value) => {
    if (value === true) {
      // Get token from localStorage
      let token = localStorage.getItem("USER_TOKEN");
      // Call deleteCategory api
      axios({
        url: `http://localhost:8080/api/admin/category/${id}`,
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
        .then((resp) => {
          swal("Thành công", "Xóa thành công!", "success").then(() => {
            loadCategories();
          });
        })
        .catch((error) => {
          console.log(error);
          swal("Thất bại", "Xóa thất bại!", "error");
        });
    }
  });
};
