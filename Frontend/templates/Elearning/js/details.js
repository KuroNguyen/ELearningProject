// Get id form url
console.log(document.location.href);
let url = new URL(document.location.href);
let id = url.searchParams.get("id");
console.log(id);

// Format currency
const formatter = new Intl.NumberFormat("vi-VN", {
  style: "currency",
  currency: "VND",
  minimumFractionDigits: 0,
});

// Get token from localStorage
let token = localStorage.getItem("USER_TOKEN");

// Render video function
const loadVideos = (videoArray) => {
  console.log(videoArray);
  let content = "";

  for (const video of videoArray) {
    content += `<li>
    <a href="#" class="btn-video" data-video-id="6xB-uXqbOqo">
      <span>
        <i class="fa fa-play-circle mr-1"></i>
        ${video.title}
      </span>
      <span>5:32</span>
    </a>
  </li>`;
  }
  document.getElementById("list-content").innerHTML = content;
};

// Render target function
const loadTargets = (targetArray) => {
  console.log(targetArray);
  let content = "";

  for (const target of targetArray) {
    content += `<div class="col-md-6">
    <ul class="course-desc-items">
      <li>
        <i class="fa fa-check"></i>
        <span
          >${target.title}</span
        >
      </li>
    </ul>
  </div>`;
  }
  document.getElementById("courseTargets").innerHTML = content;
};

const loadData = () => {
  // Call api to get course data
  axios({
    url: `http://localhost:8080/api/course/${id}`,
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log({ resp });
      // Render target
      let targetArray = resp.data.targets;
      loadTargets(targetArray);
      // Render video
      let videoArray = resp.data.videos;
      loadVideos(videoArray);
      // Render title
      document.getElementById("courseTitle").innerHTML = resp.data.title;
      // Render content
      document.getElementById("courseContent").innerHTML = resp.data.content;
      // Render last update
      document.getElementById("courseLastUpdate").innerHTML =
        resp.data.lastUpdate;
      // Render courseLectureCount
      document.getElementById("courseLectureCount").innerHTML =
        resp.data.lecturesCount;
      // Render courseHourCount
      document.getElementById("courseHourCount").innerHTML =
        resp.data.hourCount;
      // Render promotionPrice
      document.getElementById("promotionPrice").innerHTML = formatter.format(
        resp.data.promotionPrice
      );
      // Render price
      document.getElementById("price").innerHTML = formatter.format(
        resp.data.price
      );
    })
    .catch((error) => {
      console.log({ error });
    });
};

const loadCourseTargets = () => {
  // Call api to get targets
  axios({
    url: `http://localhost:8080/api/course/${id}`,
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log(resp);
    })
    .catch((error) => {
      console.log({ error });
    });
};
loadData();
