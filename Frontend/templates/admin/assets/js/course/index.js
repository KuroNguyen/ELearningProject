function loadCourse() {
  axios({
    url: "http://localhost:8080/api/admin/course",
    method: "GET",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("USER_TOKEN"),
    },
  })
    .then(function (resp) {
      //  Lấy ra mảng role
      let arrCourse = resp.data;
      console.log({ resp });
      // Tạo danh sách thẻ option
      let tableCourse = document
        .getElementById("tableCourse")
        .getElementsByTagName("tbody")[0];
      let courseRow = "";
      for (let courseDto of arrCourse) {
        courseRow += `<tr>
                <th>${courseDto.id}</th>
                <td>${courseDto.title}</td>
                <td><img height="50" class="p-1 border" src="${courseDto.image}"></td>
                <td>${courseDto.lecturesCount}</td>
                <td>${courseDto.price}</td>
                
                <td>
                    <a href="./course-edit.html?id=${courseDto.id}" class="btn btn-sm btn-info btn-round py-1 font-weight-bold">Sửa</a>
                    <button type="button" class="btn btn-sm btn-danger btn-round py-1 font-weight-bold" onclick="deleteCourse(${courseDto.id})">Xóa</button>
                </td>
            </tr>
                
                `;
      }

      tableCourse.innerHTML = courseRow;
    })
    .catch(function (err) {
      console.log(err.response);
    });
}
loadCourse();

function deleteCourse(id) {
  axios({
    url: `http://localhost:8080/api/admin/course/${id}`,
    method: "DELETE",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("USER_TOKEN"),
    },
  })
    .then(function (resp) {
      //Lấy ra mảng role
      loadCourse();
      console.log("xóa thành công");
    })
    .catch(function (err) {
      console.log(err.response);
    });
}

// xóa token
function logout() {
  localStorage.removeItem("USER_TOKEN");
  location.replace("/login.html");
}
