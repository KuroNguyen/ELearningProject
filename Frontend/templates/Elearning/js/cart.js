// Format currency
const formatter = new Intl.NumberFormat("vi-VN", {
  style: "currency",
  currency: "VND",
  minimumFractionDigits: 0,
});

// Load shopping cart
const loadShoppingCart = async () => {
  // Reference itemList
  let itemList = document.getElementById("itemList");
  // Get cart data from localStorage
  let cartItem = JSON.parse(localStorage.getItem("CART"));
  // check cartItem
  if (!cartItem) {
    itemList.innerHTML = "";
    document.getElementById("promotionPrice").innerHTML = formatter.format(0);
    document.getElementById("price").innerHTML = formatter.format(0);
    return;
  }

  // Get courses information
  let coursePromiseArray = await cartItem.map(async (item) => {
    let course = await getCourseData(item.courseId);
    return course.data;
  });
  let courses = await Promise.all(coursePromiseArray);
  console.log(courses);

  // Render cartItemList and calculate price
  let totalPrice = 0;
  let totalPromotionPrice = 0;
  let itemListContent = "";
  courses.forEach((item) => {
    totalPrice += item.price;
    totalPromotionPrice += item.promotionPrice;

    // Render part
    itemListContent += `<li style="border-style: solid; margin: 1em">
    <div class="row">
      <div class="col-md-2">
        <img
          style="padding: 2px"
          height="70"
          src="http://localhost:8080/${item.image}"
        />
      </div>
      <div class="col-md-6">
        <span
          ><strong>
            ${item.title}</strong
          >
        </span>
        <br />
        <span
          ><small>
            Dr.Angela Yu, Developer and Lead Instructor</small
          ></span
        >
      </div>
      <div class="col-md-2">
        <a href="javascript:void(0)" class="text-end" onclick="removeFromCart(${
          item.id
        })">Remove</a>
      </div>
      <div class="col-md-2">
        <span style="color: red; font-weight: bold"
          >${formatter.format(item.promotionPrice)}</span
        >
        <span class="text-decoration-line-through"
          ><small><del>${formatter.format(item.price)}</del></small>
        </span>
      </div>
    </div>
  </li>`;
  });

  itemList.innerHTML = itemListContent;
  document.getElementById("promotionPrice").innerHTML = formatter.format(
    totalPromotionPrice
  );
  document.getElementById("price").innerHTML = formatter.format(totalPrice);
};

const getCourseData = async (id) => {
  return axios.get(`http://localhost:8080/api/course/${id}`);
};

const removeFromCart = (courseId) => {
  // Iterate to get index of the item that need to be removed
  let index;
  let cartItems = JSON.parse(localStorage.getItem("CART"));

  for (let i = 0; i < cartItems.length; i++) {
    if (cartItems[i].courseId === courseId) {
      index = i;
      break;
    }
  }
  console.log(index);
  cartItems.splice(index, 1);
  console.log(cartItems);

  // Store cart to localStorage
  localStorage.setItem("CART", JSON.stringify(cartItems));
  // Reload
  location.reload();
};

const buyCourse = () => {
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");

  // Check login to execute buy course
  if (!token) {
    swal("Thông báo", "Mời bạn đăng nhập trước khi mua", "error");
    return;
  }
  // Get cartItems form localStorage
  let cartItems = JSON.parse(localStorage.getItem("CART"));

  // Generate JSON request body
  let courseDtos = [];
  cartItems.forEach((item) => {
    let courseDto = {
      id: item.courseId,
    };
    courseDtos.push(courseDto);
  });
  console.log(courseDtos);

  let buyCourseDto = {
    courseDto: courseDtos,
    totalPrice: "total",
  };
  console.log(buyCourseDto);

  // Call buyCourse api
  axios({
    url: "http://localhost:8080/api/course/buy",
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    data: buyCourseDto,
  })
    .then((resp) => {
      console.log(resp);
      swal("Thành công", "Mua khóa học thành công", "success");
    })
    .catch((error) => {
      console.log({ error });
    });
};

loadShoppingCart();
