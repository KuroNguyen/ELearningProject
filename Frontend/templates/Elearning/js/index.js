const loadCourses = () => {
  axios({
    url: `http://localhost:8080/api/course`,
    method: "GET",
  })
    .then((resp) => {
      console.log(resp);
      let courseArray = resp.data;
      let stringCourseSaleOff = "";
      let stringCoursePopular = "";
      for (const courseDto of courseArray) {
        stringCourseSaleOff += `<div class="col-md-3">
               <div class="course">
                   <img src="http://localhost:8080/${courseDto.image}" />
                   <h6 class="course-title">${courseDto.title}</h6>
                   <small class="course-content">
                   ${courseDto.content}
                   </small>
                   <div class="course-price">
                       <span>${courseDto.price} đ</span>
                       <small>${courseDto.promotionPrice} đ</small>
                   </div>
                   <div class="seller-label">Sale 10%</div>
                   <div class="course-overlay">
                       <a href="details.html?id=${courseDto.id}">
                           <h6 class="course-title">
                           ${courseDto.title}
                           </h6>
                           <div class="course-author">
                               <b>Giảng viên</b>
                               <span class="mx-1"> | </span>
                               <b>Thanh</b>
                           </div>
                           <div class="course-info">
                               <span><i class="fa fa-play-circle"></i> ${courseDto.lecturesCount} lectures</span>
                               <span class="mx-1"> | </span>
                               <span><i class="fa fa-clock-o"></i> ${courseDto.hourCount} hours</span>
                           </div>
                           <small class="course-content">
                               ${courseDto.content}
                           </small>
                       </a>
                       <a href="javascript:void(0)" class="btn btn-sm btn-danger text-white w-100" onclick="addToCart(${courseDto.id})">Add to cart</a>
                   </div>
               </div>
           </div>`;
        stringCoursePopular += `
           <div class="col-md-2">
           <div class="course">
               <img src="http://localhost:8080/${courseDto.image}" />
               <h6 class="course-title">${courseDto.title}</h6>
               <small class="course-content">
                  ${courseDto.content}
               </small>
               <div class="course-price">
                   <span>${courseDto.price} đ</span>
               </div>
               <div class="course-overlay">
                   <a href="details.html?id=${courseDto.id}">
                       <h6 class="course-title">
                           ${courseDto.title}
                       </h6>
                       <div class="course-author">
                           <b>Giảng Viên</b>
                           <span class="mx-1"> | </span>
                           <b>Thanh</b>
                       </div>
                       <div class="course-info">
                           <span><i class="fa fa-play-circle"></i> ${courseDto.lecturesCount} lectures</span>
                           <span class="mx-1"> | </span>
                           <span><i class="fa fa-clock-o"></i> ${courseDto.hourCount} hours</span>
                       </div>
                       <small class="course-content">
                       ${courseDto.content}
                       </small>
                   </a>
                   <a href="javascript:void(0)" class="btn btn-sm btn-danger text-white w-100" onclick="addToCart(${courseDto.id})">Add to cart</a>
               </div>
           </div>
           </div>`;
      }

      document.getElementById(
        "listCourseSaleOff"
      ).innerHTML = stringCourseSaleOff;
      document.getElementById(
        "listCoursePopular"
      ).innerHTML = stringCoursePopular;
    })
    .catch((error) => {
      console.log({ error });
    });
};
loadCourses();

// Render top categories
const loadCategory = () => {
  axios({
    url: `http://localhost:8080/api/category`,
    method: "GET",
  })
    .then((resp) => {
      console.log(resp);
      let categoriesContent = ``;
      let categoryArray = resp.data;
      for (const category of categoryArray) {
        categoriesContent += `<div class="col-md-3">
          <a class="category" onclick="loadCourseByCategory(${category.id})">
              <i class="${category.icon}"></i>
              <span>${category.title}</span>
          </a>
      </div>`;
      }

      document.getElementById("topCategories").innerHTML = categoriesContent;
    })
    .catch((error) => {
      console.log({ error });
    });
};
loadCategory();

const loadCourseByCategory = (id) => {
  axios({
    url: `http://localhost:8080/api/course/category/${id}`,
    method: "GET",
  }).then((resp) => {
    console.log(resp);
    let courseArray = resp.data;
    let stringCoursePopular = "";
    for (const courseDto of courseArray) {
      stringCoursePopular += `<div class="col-md-2">
      <div class="course">
          <img src="http://localhost:8080/${courseDto.image}" />
          <h6 class="course-title">${courseDto.title}</h6>
          <small class="course-content">
             ${courseDto.content}
          </small>
          <div class="course-price">
              <span>${courseDto.price} đ</span>
          </div>
          <div class="course-overlay">
              <a href="details.html">
                  <h6 class="course-title">
                      ${courseDto.title}
                  </h6>
                  <div class="course-author">
                      <b>Giảng Viên</b>
                      <span class="mx-1"> | </span>
                      <b>Thanh</b>
                  </div>
                  <div class="course-info">
                      <span><i class="fa fa-play-circle"></i> ${courseDto.lecturesCount} lectures</span>
                      <span class="mx-1"> | </span>
                      <span><i class="fa fa-clock-o"></i> ${courseDto.hourCount} hours</span>
                  </div>
                  <small class="course-content">
                  ${courseDto.content}
                  </small>
              </a>
              <a href="javascript:void(0)" class="btn btn-sm btn-danger text-white w-100" onclick="addToCart(${courseDto.id})">Add to cart</a>
          </div>
      </div>
      </div>`;
    }
    document.getElementById(
      "listCoursePopular"
    ).innerHTML = stringCoursePopular;
  });
};
