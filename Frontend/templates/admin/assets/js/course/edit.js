// Get id from URL
let url = new URL(document.location.href);
let id = url.searchParams.get("id");

function loadCategory(categoryId) {
  axios({
    url: "http://localhost:8080/api/admin/category",
    method: "GET",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("USER_TOKEN"),
    },
  })
    .then(function (resp) {
      //  Lấy ra mảng role
      let arrCate = resp.data;
      // Tạo danh sách thẻ option
      let strOption = "";
      for (let cateDto of arrCate) {
        let selected = cateDto.id === categoryId ? "selected" : "";
        strOption += `<option value="${cateDto.id}" ${selected}>${cateDto.title}</option>`;
      }
      // Truy cập tới thẻ select có id là 'roleId'
      let cateIdTag = document.getElementById("categoryId");
      // Thay thế các thẻ option cũ bằng danh sách thẻ option mới
      cateIdTag.innerHTML = strOption;
    })
    .catch(function (err) {
      console.log(err.response);
    });
}
// loadCategory();

function getFileName() {
  var fullPath = document.getElementById("image").value;
  if (fullPath) {
    var startIndex =
      fullPath.indexOf("\\") >= 0
        ? fullPath.lastIndexOf("\\")
        : fullPath.lastIndexOf("/");
    var filename = fullPath.substring(startIndex);
    if (filename.indexOf("\\") === 0 || filename.indexOf("/") === 0) {
      filename = filename.substring(1);
    }
    return filename;
  }
}

function saveImage() {
  let imageInput = document.getElementById("image");
  let formData = new FormData();
  formData.append("file", imageInput.files[0]);

  axios({
    url: "http://localhost:8080/api/admin/file/upload",
    method: "POST",
    data: formData,
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: "Bearer " + localStorage.getItem("USER_TOKEN"),
    },
  })
    .then(function (resp) {
      console.log(resp.data);
      document.getElementById("imageUrl").value = resp.data;
    })
    .catch(function (err) {
      console.log(err);
    });
}

// Load course data
const loadData = () => {
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  // Call api to get course data
  axios({
    url: `http://localhost:8080/api/admin/course/${id}`,
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log(resp);
      let course = resp.data;
      // Set value for UI element
      document.getElementById("title").value = course.title;
      document.getElementById("lecturesCount").value = course.lecturesCount;
      document.getElementById("hourCount").value = course.hourCount;
      document.getElementById("price").value = course.price;
      document.getElementById("discount").value = course.promotionPrice;
      document.getElementById("description").value = course.description;
      document.getElementById("imageUrl").value = course.image;
      document.getElementById("content").value = course.content;
      loadCategory(course.categoryId);
    })
    .catch((error) => {
      console.log({ error });
    });
};
loadData();

function editCourse() {
  let flag = true;
  let cateInput = document.getElementById("categoryId").value;
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const idCourse = urlParams.get("id");

  let titleInput = document.getElementById("title").value;
  if (titleInput.length == 0) {
    flag = false;
    document.getElementById("titleError").innerHTML = "Vui lòng nhập tiêu đề!";
  } else {
    document.getElementById("titleError").innerHTML = "";
  }

  let lecturesCountInput = document.getElementById("lecturesCount").value;
  if (lecturesCountInput == 0) {
    flag = false;
    document.getElementById("lecturesCountError").innerHTML =
      "Vui lòng nhập số bài học!";
  } else {
    document.getElementById("lecturesCountError").innerHTML = "";
  }

  let hourCountInput = document.getElementById("hourCount").value;
  if (hourCountInput == 0) {
    flag = false;
    document.getElementById("hourCountError").innerHTML =
      "Vui lòng nhập số giờ học!";
  } else {
    document.getElementById("hourCountError").innerHTML = "";
  }

  let contentInput = document.getElementById("content").value;
  if (contentInput.length == 0) {
    flag = false;
    document.getElementById("contentError").innerHTML =
      "Vui lòng nhập nội dung!";
  } else {
    document.getElementById("contentError").innerHTML = "";
  }

  let imageInput = document.getElementById("image");
  if (imageInput.files.length === 0) {
    flag = false;
    document.getElementById("imageError").innerHTML = "Vui lòng chọn hình ảnh";
  } else {
    document.getElementById("imageError").innerHTML = "";
  }

  let priceInput = document.getElementById("price").value;
  if (priceInput == 0) {
    flag = false;
    document.getElementById("priceError").innerHTML = "Vui lòng nhập giá";
  } else {
    document.getElementById("priceError").innerHTML = "";
  }

  let descriptionInput = document.getElementById("description").value;
  if (descriptionInput.length == 0) {
    flag = false;
    document.getElementById("descriptionError").innerHTML =
      "Vui lòng nhập mô tả";
  } else {
    document.getElementById("descriptionError").innerHTML = "";
  }

  let discountInput = document.getElementById("discount").value;
  if (discountInput == 0) {
    flag = false;
    document.getElementById("discountError").innerHTML =
      "Vui lòng nhập giảm giá";
  } else {
    document.getElementById("discountError").innerHTML = "";
  }

  if (flag === true) {
    // tạo user
    let courseDto = {
      id: idCourse,
      title: titleInput,
      image: document.getElementById("imageUrl").value,
      lecturesCount: lecturesCountInput,
      price: priceInput,
      hourCount: hourCountInput,
      categoryId: cateInput,
      content: contentInput,
      description: descriptionInput,
      discount: discountInput,
      lectureCount: lecturesCountInput,
    };
    console.log(courseDto);
    // GỌI API THÊM MỚI
    axios({
      url: "http://localhost:8080/api/admin/course",
      method: "PUT",
      data: courseDto,
      headers: {
        Authorization: "Bearer " + localStorage.getItem("USER_TOKEN"),
      },
    })
      .then(function (resp) {
        console.log("Thành công! " + resp.data);
        swal("Good job!", "Sửa Thành Công!", "success");
      })
      .catch(function (err) {
        console.log({ err });
        swal("Sorry", "Sửa Thất Bại!", "error");
      });
  }
}

function logout() {
  localStorage.removeItem("USER_TOKEN");
  location.replace("/login.html");
}
