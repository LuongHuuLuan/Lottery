
var resultLotteryAPi = 'http://localhost:8081/Lottery/Province/tphcm';
// function checkLink(link){
//         fetch(link).then(function(response){
//             if(response.json == null){
//                 return false;
//             }
//         })
//     }
//     console.log(checkLink(resultLotteryAPi))
function checkLink(link){
    fetch(link).then(function(response){
        if(response.ok){
            console.log('link không lỗi')
        }else{
        console.log('link lỗi')
        }
    })
}
console.log(checkLink(resultLotteryAPi));

// const randomNumber = new Promise((resolve, reject) => {
//     const url = resultLotteryAPi;
//     let request = new XMLHttpRequest();
 
//     request.open('GET', url);
//     request.onload = function() {
//        if (request.status == '200') {
//           resolve(request.response);
//        } else {
//           reject(Error(request.statusText)); 
//        }
//     };
 
//     request.onerror = function() {
//        reject(Error('Error fetching data.'));
//     };
 
//     request.send();
//  });
//  randomNumber
//  .then((res) => {
//     console.log("Success");
//     console.log("Random number: ", res);
//     return true;
//  })
//  .catch((err) => {
//     console.log("Error: ", err.message);
//     return false;
//  })
//  if(randomNumber){
//     console.log('true')
//  }else console.log('false')
//  console.log(randomNumber)
// console.log(checkLink(resultLotteryAPi));
//  function checkLink(link){
//     fetch(link).then(function(response){
//         if(response.ok){
//             console.log('link không lỗi')
//             return true
//         }
//     }).catch(function(err){
//         console.log(err)
//         if(err=='TypeError: Failed to fetch'){
//             console.log('link lỗi')
//             return false
//         }
//     })
// };
    // console.log(getAPI(resultLotteryAPi,(result) =>{
    //     return result;
    // }))
    // function getAPI(url, callback){
    //     var xmlHttp=new XMLHttpRequest();
    //     xmlHttp.onreadystatechange = function(){
    //         if(xmlHttp.readyState == 4 && xmlHttp.status == 200)
    //         callback(xmlHttp);
    //     };
    //     xmlHttp.open("GET", url,true);
    //     xmlHttp.send(null);
    // }
// function checkLink(callback){
//     fetch(callback)
//     .then(function(response){
//             // return 
//             response.json();
//             return 'OK';
//         })
//         .then(callback)
//         .catch(function(err){
//             if(err=='SyntaxError: Unexpected end of JSON input'){
//                 resultLotteryAPi=getAPIYesterday();
//                 fetch(resultLotteryAPi)
//                 .then(function(response){
//                     // return 
//                     response.json();
//                     return 'ERR';
//                 })
//                 .then(callback)
//             }
//         })
// }


// var arrProvinceCode =['bac-lieu','ben-tre','vung-tau','can-tho','dong-nai',
// 'soc-trang','an-giang','binh-thuan','tay-ninh','binh-duong','tra-vinh',
// 'vinh-long','binh-phuoc','hau-giang','long-an','tphcm','da-lat','kien-giang',
// 'tien-giang','ca-mau','dong-thap'];
// console.log(arrProvinceCode)

// function changedProvince(obj){
//     var value = obj.value;
//     resultLotteryAPi='http://localhost:8081/Lottery/Province/'+value;
//     loading();
//     display();
// }

// function display(){
//     getLottery(function(re){
//         renderNumberTable(re)  
//     },resultLotteryAPi)
// }
// function renderNumberTable(results){
//     var numberTable = document.querySelector('.table-lottery');
//     var htmls = results.map(function(result){
//         return`
//         <table>
//             <thead class="title_table">
//             <th style="padding: 12px;" colspan="2"><h2>
//             Kết quả Xổ Số Tỉnh ${result.province} - ${result.date}
//             </h2></th>
//             </thead>
//                 <thead>
//                 <tr class="list_province">
//                 <th>Giải</th>
//                 <th><h3>Kết Quả</h3></th>
//                 </tr>
//             </thead>
//              <tbody>
//                 <tr class="list_result_8">
//                 <th>G.8</th>
//                 <td><span class="font-red result-lottery">${result.results[0].results}</span></td>
//                 </tr>
//                 <tr class="list_result_7">
//                 <th>G.7</th>
//                 <td><span class="result-lottery">${result.results[1].results}</span></td>
//                 </tr>
//                 <tr class="list_result_6">
//                 <th>G.6</th>
//                 <td><span class="result-lottery">${result.results[2].results}</span></td>
//                 </tr>
//                 <tr class="list_result_5">
//                 <th>G.5</th>
//                 <td><span class="result-lottery">${result.results[3].results}</span></td>
//                 </tr>
//                 <tr class="list_result_4">
//                 <th>G.4</th>
//                 <td><span class="result-lottery">${result.results[4].results}</span></td>
//                 </tr>
//                 <tr class="list_result_3">
//                 <th>G.3</th>
//                 <td><span class="result-lottery">${result.results[5].results}</span></td>
//                 </tr>
//                 <tr class="list_result_2">
//                 <th>G.2</th>
//                 <td><span class="result-lottery">${result.results[6].results}</span></td>
//                 </tr> 
//                 <tr class="list_result_1">
//                 <th>G.1</th>
//                 <td><span class="result-lottery">${result.results[7].results}</span></td>
//                 </tr>
//                 <tr class="list_result_DB">
//                 <th>G.ĐB</th>
//                 <td><span class="font-red result-lottery">${result.results[8].results}</span></td>
//                 </tr>
//             </tbody>
//          </table>
//         `;
//     })
//     numberTable.innerHTML = htmls.join('');
// }

// function loading(){
//     var load = document.querySelector('.loading');
//     load.classList.add('open');
//     setTimeout(function(){
//         load.classList.remove('open');
//     },500);
// }


