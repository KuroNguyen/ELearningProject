// KIỂM TRA ĐĂNG NHẬP
function checkLogin() {
  // LẤY TOKEN TỪ LOCALSTORAGE
  let token = localStorage.getItem("USER_TOKEN");
  if (token == null || token.length == 0) {
    document.location.href = "../auth/login.html";
  }
}

// ĐĂNG XUẤT
function logout() {
  // LẤY TOKEN TỪ LOCALSTORAGE
  let token = localStorage.getItem("USER_TOKEN");
  if (token != null) {
    // XÓA TOKEN KHỎI LOCALSTORAGE
    localStorage.removeItem("USER_TOKEN");
    document.location.href = "../auth/login.html";
  }
}

// Load navBar function
const loadNavBar = () => {
  // Reference navBar from DOM
  let navBar = document.getElementById("navBar");
  // Set navBar content
  let content = `<div class="navbar-wrapper">
    <div class="navbar-logo">
      <a class="mobile-menu" id="mobile-collapse" href="#!">
        <i class="feather icon-menu"></i>
      </a>
      <a href="#">
        <img
          class="img-fluid"
          src="../assets/images/logo.png"
          alt="Theme-Logo"
        />
      </a>
      <a class="mobile-options">
        <i class="feather icon-more-horizontal"></i>
      </a>
    </div>

    <div class="navbar-container container-fluid">
      <ul class="nav-left">
        <li class="header-search">
          <div class="main-search morphsearch-search">
            <div class="input-group">
              <span class="input-group-addon search-close"
                ><i class="feather icon-x"></i
              ></span>
              <input type="text" class="form-control" />
              <span class="input-group-addon search-btn"
                ><i class="feather icon-search"></i
              ></span>
            </div>
          </div>
        </li>
      </ul>
      <ul class="nav-right">
        <li class="header-notification">
          <div class="dropdown-primary dropdown">
            <div class="dropdown-toggle" data-toggle="dropdown">
              <i class="feather icon-bell"></i>
              <span class="badge bg-c-pink">5</span>
            </div>
          </div>
        </li>
        <li class="header-notification">
          <div class="dropdown-primary dropdown">
            <div
              class="displayChatbox dropdown-toggle"
              data-toggle="dropdown"
            >
              <i class="feather icon-message-square"></i>
              <span class="badge bg-c-green">3</span>
            </div>
          </div>
        </li>
        <li class="user-profile header-notification">
          <div class="dropdown-primary dropdown">
            <div class="dropdown-toggle" data-toggle="dropdown">
              <img
                src="../assets/images/avatar-4.jpg"
                class="img-radius"
                alt="User-Profile-Image"
              />
              <span>Kuro Nguyen</span>
              <i class="feather icon-chevron-down"></i>
            </div>
            <ul
              class="show-notification profile-notification dropdown-menu"
              data-dropdown-in="fadeIn"
              data-dropdown-out="fadeOut"
            >
              <li>
                <a href="#!">
                  <i class="feather icon-settings"></i> Settings
                </a>
              </li>
              <li>
                <a href="user-profile.htm">
                  <i class="feather icon-user"></i> Profile
                </a>
              </li>
              <li>
                <a href="auth-normal-sign-in.htm">
                  <i class="feather icon-log-out"></i> Logout
                </a>
              </li>
            </ul>
          </div>
        </li>
      </ul>
    </div>
  </div>`;
  navBar.innerHTML = content;
};

// Load sideBar function
const loadSideBar = () => {
  let sideBar = document.getElementById("sideBar");
  let content = `<div class="pcoded-inner-navbar main-menu">
    <ul class="pcoded-item pcoded-left-item">
      <li class="">
        <a href="javascript:void(0)">
          <span class="pcoded-micon"
            ><i class="feather icon-home"></i
          ></span>
          <span class="pcoded-mtext">Dashboard</span>
        </a>
      </li>
      <li class="">
        <a href="../../../../admin/category/category-index.html">
          <span class="pcoded-micon"
            ><i class="feather icon-layers"></i
          ></span>
          <span class="pcoded-mtext">Danh mục</span>
        </a>
      </li>
      <li class="">
        <a href="../../../../admin/course/course-list.html">
          <span class="pcoded-micon"
            ><i class="feather icon-layers"></i
          ></span>
          <span class="pcoded-mtext">Khóa học</span>
        </a>
      </li>
      <li class="">
        <a href="../../../../admin/video/video-list.html">
          <span class="pcoded-micon"
            ><i class="feather icon-layers"></i
          ></span>
          <span class="pcoded-mtext">Video</span>
        </a>
      </li>
      <li class="">
        <a href="../../../../admin/target/target-list.html">
          <span class="pcoded-micon"
            ><i class="feather icon-layers"></i
          ></span>
          <span class="pcoded-mtext">Mục tiêu</span>
        </a>
      </li>
      <li class="">
        <a href="../../../../admin/user/user-index.html">
          <span class="pcoded-micon"
            ><i class="feather icon-user"></i
          ></span>
          <span class="pcoded-mtext">Tài khoản</span>
        </a>
      </li>
      <li class="">
        <a href="../../../../admin/role/role-index.html">
          <span class="pcoded-micon"
            ><i class="feather icon-user"></i
          ></span>
          <span class="pcoded-mtext">Quyền</span>
        </a>
      </li>
    </ul>
  </div>`;

  sideBar.innerHTML = content;
};

checkLogin();
loadNavBar();
loadSideBar();
