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
    url: "http://localhost:8080/api/auth/login",
    method: "POST",
    data: loginModel,
  })
    .then((resp) => {
      console.log({ resp });
      // Clear information
      document.getElementById("lgEmail").value = "";
      document.getElementById("lgPassword").value = "";

      // Store token into localStorage
      localStorage.setItem("USER_TOKEN", resp.data.token);

      // Store user information into localStorage
      let userModel = {
        id: resp.data.userId,
        name: resp.data.userName,
      };
      localStorage.setItem("USER_INFO", JSON.stringify(userModel));

      location.reload();
    })
    .catch((error) => {
      console.log({ ...error });
    });
};

const logout = () => {
  // Get USER_TOKEN and USER_INFO from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  let userModel = localStorage.getItem("USER_INFO");
  if (token != null && userModel != null) {
    // Remove USER_TOKEN and USER_INFO from localStorage
    localStorage.removeItem("USER_TOKEN");
    localStorage.removeItem("USER_INFO");
  }

  location.reload();
};

const loadCategory = () => {
  axios({
    url: `http://localhost:8080/api/admin/course/${id}`,
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

// Load navBar function
const loadNavBar = () => {
  // Reference navBar from DOM
  let navBar = document.getElementById("navBar");
  // Set navBar content
  let content = `<a class="navbar-brand" href="index.html">Elearning</a>
    <button
      class="navbar-toggler d-lg-none"
      type="button"
      data-toggle="collapse"
      data-target="#collapsibleNavId"
      aria-controls="collapsibleNavId"
      aria-expanded="false"
      aria-label="Toggle navigation"
    ></button>
    <div class="collapse navbar-collapse row" id="collapsibleNavId">
      <div class="col-md-6">
        <ul
          class="navbar-nav mr-auto mt-2 mt-lg-0 d-flex align-items-center justify-content-between"
        >
          <li class="nav-item mr-3">
            <div class="dropdown">
              <a
                class="btn btn-outline-light dropdown-toggle"
                data-toggle="dropdown"
              >
                <i class="fa fa-th"></i>
                <span class="ml-2">Categories</span>
              </a>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="#">
                  <i class="fa fa-laptop mr-1"></i>
                  <span>Development</span>
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fa fa-camera-retro mr-1"></i>
                  <span>Photography</span>
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fa fa-bar-chart mr-1"></i>
                  <span>Business</span>
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fa fa-desktop mr-1"></i>
                  <span>IT & Software</span>
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fa fa-handshake-o mr-1"></i>
                  <span>Marketing</span>
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fa fa-music mr-1"></i>
                  <span>Music</span>
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fa fa-pencil-square-o mr-1"></i>
                  <span>Design</span>
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fa fa-cutlery mr-1"></i>
                  <span>Cooking</span>
                </a>
              </div>
            </div>
          </li>
          <li class="nav-item w-100">
            <form class="input-group nav-search">
              <input
                type="text"
                class="form-control"
                placeholder="Search courses"
              />
              <div class="input-group-append">
                <button class="btn bg-white text-danger">
                  <i class="fa fa-search"></i>
                </button>
              </div>
            </form>
          </li>
        </ul>
      </div>
      <div class="col-md-1 nav-cart">
        <i class="fa fa-shopping-basket"></i>
      </div>`;
  `<div class="col-md-5 text-right">
        <button
          class="btn btn-outline-secondary"
          data-toggle="modal"
          data-target="#loginModal"
        >
          Login
        </button>
        <button
          class="btn btn-danger ml-2"
          data-toggle="modal"
          data-target="#signUpModal"
        >
          Sign up
        </button>
      </div>
    </div>`;
  // Check login state
  // Get userModel from localStorage
  let userModel = JSON.parse(localStorage.getItem("USER_INFO"));
  console.log({ userModel });
  // if user not login, load login and sign up button
  if (userModel == null) {
    content += `<div class="col-md-5 text-right">
    <button
      class="btn btn-outline-secondary"
      data-toggle="modal"
      data-target="#loginModal"
    >
      Login
    </button>
    <button
      class="btn btn-danger ml-2"
      data-toggle="modal"
      data-target="#signUpModal"
    >
      Sign up
    </button>
  </div>`;
  } else {
    // display userName
    content += `<div class="col-md-5 d-flex justify-content-end">
    <div class="dropdown">
        <div class="dropdown-toggle font-weight-bold text-dark" data-toggle="dropdown">
            ${userModel.name}
        </div>
        <div class="dropdown-menu dropdown-menu-right">
            <a class="dropdown-item" href="profile.html?id=${userModel.id}">Thông tin cá nhân</a>
            <a class="dropdown-item" href="course.html?id=${userModel.id}">Khóa học của tôi</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#" onclick="logout()">Đăng xuất</a>
        </div>
    </div>
</div>`;
  }

  content += `</div>`;
  navBar.innerHTML = content;
};
loadNavBar();
