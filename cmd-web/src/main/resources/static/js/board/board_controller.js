/**
 * Created by Coupang on 2015-12-08.
 */
'use strict';

App.controller('AppController', ['$scope', 'BoardService', function($scope, BoardService) {
	var self = this;

	self.boards=[];

	self.main = {
		page: 1,
		size: 10,
		totalPages: 10,
		firstPage: true,
		lastPage: false,
		totalElements: 10,
	};

	self.pageBtnArray =[];       //한화면에 보여지는 페이지버튼index가 들어있는 배열
	self.btnArrayDefaultCnt = 5; //한화면에 보여지는 페이지버튼 수
	self.btnChunkIndex = 0;      //버튼배열 덩어리의 총 갯수
	self.startPageBtnNumber = 1; //버튼배열의 첫번째 버튼번호
	self.currentChunkNumber = 1; //현재 버튼배열 덩어리의 index
	self.btnChunkCnt = 0;

	self.settingPageCtrl = function(totalPages, currentPage) {

		self.btnChunkCnt = parseInt(totalPages / self.btnArrayDefaultCnt) + 1;   //버튼덩어리 수
		var lastChunkBtnCnt = totalPages % self.btnArrayDefaultCnt; //마지막 chunk 의 버튼갯수

		self.startPageBtnNumber = self.btnChunkIndex * self.btnArrayDefaultCnt + 1; //페이지버튼의 시작넘버

		self.pageBtnArray = new Array(); //버튼배열 초기화
		if (self.btnChunkCnt == self.currentChunkNumber) { //마지막 btn chunk 이면
			for (var i = 0; i < lastChunkBtnCnt; i++) {
				self.pageBtnArray[i] = self.startPageBtnNumber + i;
			}
		} else {
			for (var i = 0; i < self.btnArrayDefaultCnt; i++) {
				self.pageBtnArray[i] = self.startPageBtnNumber + i;
			}
		}
	}

	self.getPageBtnClass = function(n) {
		//alert(n + ":" + self.main.page);
		return n == self.main.page ? "active" : "";
	}

	self.getPrevPageBtnClass = function() {
		if (self.currentChunkNumber == 1) {
			return "prev disabled";
		} else {
			return "prev";
		}
	}

	self.getNextPageBtnClass = function() {
		if (self.currentChunkNumber == self.btnChunkCnt) {
			return "next disabled";
		} else {
			return "next";
		}
	}

	self.getPreviousPageClass = function() {
		//alert(self.currentChunkNumber + ":" + self.btnArrayDefaultCnt );
		return self.main.page > self.startPageBtnNumber ? "" : "disabled";
	}

	self.getNextPageClass = function() {
		return self.main.page < self.currentChunkNumber * self.btnArrayDefaultCnt ? "" : "disabled";
	}

	/**
	 * 다음 버튼배열 덩어리로 이동
	 */
	self.newNextPage = function() {
		if (self.currentChunkNumber == self.btnChunkCnt) {
			return;
		}
		self.main.page = self.startPageBtnNumber + self.btnArrayDefaultCnt; //다음화면의 페이지버튼의 시작넘버
		self.btnChunkIndex++;
		self.currentChunkNumber++;
		//alert(self.main.page);
		self.fetchBoards();

	};

	/**
	 * 이전 버튼배열 덩어리로 이동
	 */
	self.newPreviousPage = function() {
		if (self.currentChunkNumber == 1) {
			return;
		}
		self.main.page = self.startPageBtnNumber - self.btnArrayDefaultCnt; //이전화면의 페이지버튼의 시작넘버
		self.btnChunkIndex--;
		self.currentChunkNumber--;
		//alert(self.main.page);
		self.fetchBoards();

	};

	self.mainInit = function() {
		self.main.page = 1;
		self.main.size = 10;
		self.main.totalPages =10;
		self.main.firstPage = true;
		self.main.lastPage = false;
		self.main.totalElements = 10;
	}


	self.fetchBoards = function(){
		BoardService.fetchBoards(self.main)
			.then(
			function(d) {
				self.boards = d.content;
				self.main.page = d.number + 1;
				self.main.size = d.size;
				self.main.totalPages = d.totalPages;
				self.main.firstPage = d.firstPage;
				self.main.lastPage = d.lastPage;
				self.main.totalElements = d.totalElements;

				self.settingPageCtrl(d.totalPages, d.number + 1);  //버튼배열 세팅
			},
			function(errResponse){
				console.error('Error while fetching Currencies');
			}
		);
	};

	self.fetchBoards();

	self.nextPage = function() {   //다음버튼의 페이지로 이동
		if (self.main.page < self.currentChunkNumber * self.btnArrayDefaultCnt) {
			self.main.page++;
			self.fetchBoards();
		}
	};

	self.previousPage = function() {  //이전버튼의 페이지로 이동
		if (self.main.page > self.startPageBtnNumber) {
			self.main.page--;
			self.fetchBoards();
		}
	};

	self.movePage = function(currentPage) {   //선택한 버튼의 페이지로 이동
		self.main.page = currentPage;
		self.fetchBoards();
	};

	/*self.searchParam = {
		dateBegin: '',
		dateEnd: '',
		shippingCompanyCode:''
	}*/
	//self.search();
	/*self.search = function() {
		self.mainInit(); //페이징관련 초기화
		var $datePicker = $("#order-datepicker");
		var datepickerData = $datePicker.data('daterangepicker');
		self.searchParam.dateBegin = datepickerData.startDate.format("YYYY-MM-DD");
		self.searchParam.dateEnd = datepickerData.endDate.format("YYYY-MM-DD");

		BoardService.searchBoards(self.main, self.searchParam)
			.then(
			function(d) {
				self.boards = d.content;
				self.main.page = d.number + 1;
				self.main.size = d.size;
				self.main.totalPages = d.totalPages;
				self.main.firstPage = d.firstPage;
				self.main.lastPage = d.lastPage;
				self.main.totalElements = d.totalElements;
			},
			function(errResponse){
				console.error('Error while fetching Currencies');
			}
		);
	}*/

}]);
