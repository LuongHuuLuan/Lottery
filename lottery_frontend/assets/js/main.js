var resultLotteryAPi = "http://localhost:8081/Lottery/Date";

var today = new Date();

function getAPIYesterday() {
  today.setDate(today.getDate() - 1);
  var day = today.getDate();
  var month = today.getMonth() + 1;
  var year = today.getFullYear();
  return "http://localhost:8081/Lottery/Date/" + day + "-" + month + "-" + year;
}

function getLinkAPIByDay(targetDay) {
  let selectValue = targetDay == 0 ? 7 : targetDay;
  let day = today.getDay() == 0 ? 7 : today.getDay();
  let targetDate = new Date();
  let differentDate = selectValue - day;
  if (selectValue > day) {
    targetDate.setDate(today.getDate() + differentDate - 7);
  } else {
    targetDate.setDate(today.getDate() + differentDate);
  }
  return (
    "http://localhost:8081/Lottery/Date/" +
    targetDate.getDate() +
    "-" +
    (targetDate.getMonth() + 1) +
    "-" +
    targetDate.getFullYear()
  );
}

function changedDay(obj) {
  resultLotteryAPi = getLinkAPIByDay(obj.value);
  resetTable();
  loading();
  start();
}

function searchProvince(code) {
  resultLotteryAPi = "http://localhost:8081/Lottery/Province/" + code;
  loading();
  display();
}

function clickNull() {
  alert("Chưa có dữ liệu !!!");
}
function searchDay(number) {
  resultLotteryAPi = getLinkAPIByDay(number);
  resetTable();
  loading();
  start();
}
function search() {
  var inputDate = document.getElementById("input_date").value;
  var arr = inputDate.split("-");
  let date = arr[2];
  let month = arr[1];
  let year = arr[0];
  resultLotteryAPi =
    "http://localhost:8081/Lottery/Date/" + date + "-" + month + "-" + year;
  fetch(resultLotteryAPi).then((response) => {
    if (response.ok) {
      resetTable();
      loading();
      start();
    } else {
      alert("Không có kết quả");
    }
  });
}

function start() {
  getLottery(function (re) {
    renderProvince(re);
    renderResult_8(re);
    renderResult_7(re);
    renderResult_6(re);
    renderResult_5(re);
    renderResult_4(re);
    renderResult_3(re);
    renderResult_2(re);
    renderResult_1(re);
    renderResult_DB(re);
    renderTitleTable(re);
  }, resultLotteryAPi);
}
start();

function getLottery(callback, linkAPI) {
  fetch(linkAPI)
    .then(function (response) {
      return response.json();
    })
    .then(callback)
    .catch(function (err) {
      if (err == "SyntaxError: Unexpected end of JSON input") {
        linkAPI = getAPIYesterday();
        fetch(linkAPI)
          .then(function (response) {
            return response.json();
          })
          .then(callback);
      }
    });
}

function renderProvince(results) {
  var tableResults = document.querySelector(".list_province");
  var htmls = results.map(function (result) {
    return `
        <th>${result.province}</th>
        `;
  });
  tableResults.innerHTML = "<th>Giải</th>" + htmls.join("");
}

function renderResult_8(results) {
  var results_8 = document.querySelector(".list_result_8");
  var htmls8 = results.map(function (result) {
    for (let i = 0; i < result.results.length; i++) {
      var item = result.results[i];
      if (item.prizeName == "Giải 8") {
        return `
                <td><span class="font-red result-lottery">${item.results}</span></td>
        `;
      }
    }
  });
  results_8.innerHTML = "<th>G.8</th>" + htmls8.join("");
}
function renderResult_7(results) {
  var results_7 = document.querySelector(".list_result_7");
  var htmls8 = results.map(function (result) {
    for (let i = 0; i < result.results.length; i++) {
      var item = result.results[i];
      if (item.prizeName == "Giải 7") {
        return `
                <td><span class="result-lottery">${item.results}</span></td>
        `;
      }
    }
  });
  results_7.innerHTML = "<th>G.7</th>" + htmls8.join("");
}
function renderResult_6(results) {
  var results_6 = document.querySelector(".list_result_6");
  var htmls6 = results.map(function (result) {
    for (let i = 0; i < result.results.length; i++) {
      var item = result.results[i];
      if (item.prizeName == "Giải 6") {
        let arr = item.results.split(" ");
        return `
                    <td>
                        <span class="result-lottery">${arr[0]}</span>
                        <span class="result-lottery">${arr[1]}</span>
                        <span class="result-lottery">${arr[2]}</span>
                    </td>
            `;
      }
    }
  });
  results_6.innerHTML = "<th>G.6</th>" + htmls6.join("");
}
function renderResult_5(results) {
  var results_5 = document.querySelector(".list_result_5");
  var htmls5 = results.map(function (result) {
    for (let i = 0; i < result.results.length; i++) {
      var item = result.results[i];
      if (item.prizeName == "Giải 5") {
        return `
                <td><span class="result-lottery">${item.results}</span></td>
        `;
      }
    }
  });
  results_5.innerHTML = "<th>G.5</th>" + htmls5.join("");
}
function renderResult_4(results) {
  var results_4 = document.querySelector(".list_result_4");
  var htmls4 = results.map(function (result) {
    for (let i = 0; i < result.results.length; i++) {
      var item = result.results[i];
      if (item.prizeName == "Giải 4") {
        let arr = item.results.split(" ");
        return `
                    <td>
                        <span class="result-lottery">${arr[0]}</span>
                        <span class="result-lottery">${arr[1]}</span>
                        <span class="result-lottery">${arr[2]}</span>
                        <span class="result-lottery">${arr[3]}</span>
                        <span class="result-lottery">${arr[4]}</span>
                        <span class="result-lottery">${arr[5]}</span>
                        <span class="result-lottery">${arr[6]}</span>
                    </td>
            `;
      }
    }
  });
  results_4.innerHTML = "<th>G.4</th>" + htmls4.join("");
}
function renderResult_3(results) {
  var results_3 = document.querySelector(".list_result_3");
  var htmls3 = results.map(function (result) {
    for (let i = 0; i < result.results.length; i++) {
      var item = result.results[i];
      if (item.prizeName == "Giải 3") {
        let arr = item.results.split(" ");
        return `
                    <td>
                        <span class="result-lottery">${arr[0]}</span>
                        <span class="result-lottery">${arr[1]}</span>
                    </td>
            `;
      }
    }
  });
  results_3.innerHTML = "<th>G.3</th>" + htmls3.join("");
}
function renderResult_2(results) {
  var results_2 = document.querySelector(".list_result_2");
  var htmls2 = results.map(function (result) {
    for (let i = 0; i < result.results.length; i++) {
      var item = result.results[i];
      if (item.prizeName == "Giải 2") {
        return `
                <td><span class="result-lottery">${item.results}</span></td>
        `;
      }
    }
  });
  results_2.innerHTML = "<th>G.2</th>" + htmls2.join("");
}
function renderResult_1(results) {
  var results_1 = document.querySelector(".list_result_1");
  var htmls1 = results.map(function (result) {
    for (let i = 0; i < result.results.length; i++) {
      var item = result.results[i];
      if (item.prizeName == "Giải 1") {
        return `
                <td><span class="result-lottery">${item.results}</span></td>
        `;
      }
    }
  });
  results_1.innerHTML = "<th>G.1</th>" + htmls1.join("");
}
function renderResult_DB(results) {
  var results_DB = document.querySelector(".list_result_DB");
  var htmlsDB = results.map(function (result) {
    for (let i = 0; i < result.results.length; i++) {
      var item = result.results[i];
      if (item.prizeName == "Giải ĐB") {
        return `
                <td><span class="font-red result-lottery">${item.results}</span></td>
        `;
      }
    }
  });
  results_DB.innerHTML = "<th>G.ĐB</th>" + htmlsDB.join("");
}
function renderTitleTable(results) {
  var date = "";
  var col = 1;
  var titleTable = document.querySelector(".title_table");
  results.map(function (result) {
    date = result.date;
    col++;
  });
  titleTable.innerHTML =
    '<th style="padding: 12px;" colspan="' +
    col +
    '"><h2>Kết quả Xổ số Miền Nam - ' +
    date +
    "</h2></th>";
}
function resetTable() {
  var numberTable = document.querySelector(".table-lottery");
  var htmls = `<table>
        <thead class="title_table"></thead>
        <thead>
            <tr class="list_province"></tr>
        </thead>
        <tbody>
            <tr class="list_result_8"></tr>
            <tr class="list_result_7"></tr>
            <tr class="list_result_6"></tr>
            <tr class="list_result_5"></tr>
            <tr class="list_result_4"></tr>
            <tr class="list_result_3"></tr>
            <tr class="list_result_2"></tr>
            <tr class="list_result_1"></tr>
            <tr class="list_result_DB"></tr>
        </tbody>
    </table>`;
  numberTable.innerHTML = htmls;
}
function changedProvince(obj) {
  var value = obj.value;
  resultLotteryAPi = "http://localhost:8081/Lottery/Province/" + value;
  loading();
  display();
}

function display() {
  getLottery(function (re) {
    renderNumberTable(re);
  }, resultLotteryAPi);
}
function renderNumberTable(results) {
  var numberTable = document.querySelector(".table-lottery");
  var htmls = results.map(function (result) {
    return `
            <table>
                <thead class="title_table">
                <th style="padding: 12px;" colspan="2"><h2>
                Kết quả Xổ Số Tỉnh ${result.province} - ${result.date}
                </h2></th>
                </thead>
                    <thead>
                    <tr class="list_province">
                    <th>Giải</th>
                    <th><h3>Kết Quả</h3></th>
                    </tr>
                </thead>
                 <tbody>
                    <tr class="list_result_8">
                    <th>G.8</th>
                    <td><span class="font-red result-lottery">${result.results[0].results}</span></td>
                    </tr>
                    <tr class="list_result_7">
                    <th>G.7</th>
                    <td><span class="result-lottery">${result.results[1].results}</span></td>
                    </tr>
                    <tr class="list_result_6">
                    <th>G.6</th>
                    <td><span class="result-lottery">${result.results[2].results}</span></td>
                    </tr>
                    <tr class="list_result_5">
                    <th>G.5</th>
                    <td><span class="result-lottery">${result.results[3].results}</span></td>
                    </tr>
                    <tr class="list_result_4">
                    <th>G.4</th>
                    <td><span class="result-lottery">${result.results[4].results}</span></td>
                    </tr>
                    <tr class="list_result_3">
                    <th>G.3</th>
                    <td><span class="result-lottery">${result.results[5].results}</span></td>
                    </tr>
                    <tr class="list_result_2">
                    <th>G.2</th>
                    <td><span class="result-lottery">${result.results[6].results}</span></td>
                    </tr>
                    <tr class="list_result_1">
                    <th>G.1</th>
                    <td><span class="result-lottery">${result.results[7].results}</span></td>
                    </tr>
                    <tr class="list_result_DB">
                    <th>G.ĐB</th>
                    <td><span class="font-red result-lottery">${result.results[8].results}</span></td>
                    </tr>
                </tbody>
             </table>
            `;
  });
  numberTable.innerHTML = htmls.join("");
}

function loading() {
  var load = document.querySelector(".loading");
  load.classList.add("open");
  setTimeout(function () {
    load.classList.remove("open");
  }, 500);
}
