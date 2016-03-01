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
	self.startPageBtnNumber = 1; //버튼배열의 첫번째 버튼번호
	self.currentChunkNumber = 1; //현재 버튼배열 덩어리의 index
	self.btnChunkCnt = 0;        //버튼배열 덩어리의 총 갯수
	self.lastChunkBtnCnt = 0;    //마지막 chunk 의 버튼갯수

	self.settingPageCtrl = function(totalPages, currentPage) {

		self.btnChunkCnt = parseInt(totalPages / self.btnArrayDefaultCnt) + 1;   //버튼덩어리 수
		self.lastChunkBtnCnt = totalPages % self.btnArrayDefaultCnt; //마지막 chunk 의 버튼갯수

		self.startPageBtnNumber = ((self.currentChunkNumber - 1) * self.btnArrayDefaultCnt) + 1; //페이지버튼의 시작넘버

		self.pageBtnArray = new Array(); //버튼배열 초기화
		if (self.btnChunkCnt == self.currentChunkNumber) { //마지막 btn chunk 이면
			for (var i = 0; i < self.lastChunkBtnCnt; i++) {
				self.pageBtnArray[i] = self.startPageBtnNumber + i;
			}
		} else {
			for (var i = 0; i < self.btnArrayDefaultCnt; i++) {
				self.pageBtnArray[i] = self.startPageBtnNumber + i;
			}
		}
	}

	self.getEmptyDataClass = function() {
		return self.main.totalElements == 0 ? "" : "hidden";
	}

	/**
	 * ( >> click ) 다음의 버튼배열 덩어리로 이동
	 */
	self.nextPageChunk = function() {
		if (self.currentChunkNumber == self.btnChunkCnt) {
			return;
		}
		self.main.page = self.startPageBtnNumber + self.btnArrayDefaultCnt; //다음화면의 페이지버튼의 시작넘버
		self.currentChunkNumber++;
		self.fetchBoards();

	};

	/**
	 * >> 버튼 class 가져오기
	 * @returns {*}
	 */
	self.getNextPageChunkClass = function() {
		if (self.currentChunkNumber == self.btnChunkCnt) {
			return "next disabled";
		} else {
			return "next";
		}
	}

	/**
	 * ( << click ) 이전의 버튼배열 덩어리로 이동
	 */
	self.previousPageChunk = function() {
		if (self.currentChunkNumber == 1) {
			return;
		}
		self.main.page = self.startPageBtnNumber - self.btnArrayDefaultCnt; //이전화면의 페이지버튼의 시작넘버
		self.currentChunkNumber--;
		self.fetchBoards();
	};

	/**
	 * << 버튼 class 가져오기
	 * @returns {*}
	 */
	self.getPreviousPageChunkClass = function() {
		if (self.currentChunkNumber == 1) {
			return "prev disabled";
		} else {
			return "prev";
		}
	}

	/**
	 * (다음 버튼 click) 다음 페이지로 이동
	 */
	self.nextPage = function() {
		if (self.pageBtnArray.length == 1 || self.main.totalPages == self.main.page) {
			return;
		}

		if (self.main.page == self.currentChunkNumber * self.btnArrayDefaultCnt) {
			self.nextPageChunk();
		} else if (self.main.page < self.currentChunkNumber * self.btnArrayDefaultCnt) {
			self.main.page++;
			self.fetchBoards();
		}
	};

	/**
	 * 다음 버튼 class 가져오기
	 * @returns {*}
	 */
	self.getNextPageClass = function() {
		//alert(self.main.page + ":" + self.main.totalPages);
		if (self.pageBtnArray.length == 1 || self.main.totalPages == self.main.page) {
			return "disabled";
		}

		if (self.main.page == self.currentChunkNumber * self.btnArrayDefaultCnt) {
			return "";
		} else if (self.main.page < self.currentChunkNumber * self.btnArrayDefaultCnt) {
			return "";
		} else {
			return "disabled";
		}
	}

	/**
	 * (이전 버튼 click) 이전 페이지로 이동
	 */
	self.previousPage = function() {
		if (self.currentChunkNumber > 1 && self.main.page == self.startPageBtnNumber) { //첫번째 chunk가 아니고 현재페이지가 현재chunk의 첫번째btn이면
			if (self.currentChunkNumber == 1) {
				return;
			}

			//alert(self.startPageBtnNumber + ":" + self.btnArrayDefaultCnt);
			//self.main.page = self.startPageBtnNumber - self.btnArrayDefaultCnt; //이전화면의 페이지버튼의 시작넘버
			self.main.page = self.startPageBtnNumber - 1; //이전화면의 페이지버튼의 시작넘버
			self.currentChunkNumber--;
			self.fetchBoards();

		} else if (self.main.page > self.startPageBtnNumber) {
			self.main.page--;
			self.fetchBoards();
		}
	};

	/**
	 * 이전 버튼 class 가져오기
	 * @returns {string}
	 */
	self.getPreviousPageClass = function() {
		if (self.currentChunkNumber > 1 && self.main.page == self.startPageBtnNumber) {
			return "";
		} else if (self.main.page > self.startPageBtnNumber) {
			return "";
		} else {
			return "disabled";
		}
	}

	/**
	 * 선택 (click) 한 페이지로 이동
	 * @param currentPage
	 */
	self.movePage = function(currentPage) {
		self.main.page = currentPage;
		self.fetchBoards();
	};

	/**
	 * 페이지 버튼 class 가져오기
	 * @param n
	 * @returns {string}
	 */
	self.getPageBtnClass = function(n) {
		return n == self.main.page ? "active" : "";
	}

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
