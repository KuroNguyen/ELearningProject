const loadData = () => {
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");

  // Get user information from localStorage
  let user = JSON.parse(localStorage.getItem("USER_INFO"));
  // Render user name
  document.getElementById("userName").innerHTML = user.name;
  // Call api to get courseByUserId
  axios({
    url: "http://localhost:8080/api/course/learning",
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log(resp);
      let content = "";
      let courseArray = resp.data;
      courseArray.forEach((course) => {
        content += `<div class="col-md-3">
            <a href="#" class="my-course-item">
              <img height="200" width="100" src="http://localhost:8080/${course.image}" alt="" />
              <h6 class="my-course-title">
                ${course.title}
              </h6>
              <div class="my-course-desc">
                ${course.description}
              </div>
              <div class="my-course-author">
                <h6>
                  <small>Thanh</small>
                  <small>Start course</small>
                </h6>
              </div>
            </a>
          </div>`;
      });

      document.getElementById("courseList").innerHTML = content;
    })
    .catch((error) => {
      console.log({ error });
    });
};
loadData();
