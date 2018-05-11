"use strict";
//enum RequestMethod
//{
//	GET = "GET",
//	POST = "POST",
//	PUT = "PUT",
//	DELETE = "DELETE",
//	PATCH = "PATCH"
//}
//
//enum RequestState
//{
//	NEW = 0,
//	OPENED,
//	SENT,
//	PATRYALLY_READY,
//	READY
//}
//
//type StateChangedEvent = (state: RequestState, statusCode: number) => void;
//
//class AJAX
//{
//	private method: RequestMethod;
//	private handler: string;
//	private onSuccess: StateChangedEvent[];
//	private onFailure: StateChangedEvent[];
//	private onSent: StateChangedEvent[];
//
//	constructor(method: RequestMethod, handler: string)
//	{
//		this.method = method;
//		this.handler = handler;
//	}
//
//	public addOnSuccessHandler(functor: StateChangedEvent)
//	{
//		this.onSuccess.push(functor);
//	}
//
//	public addOnSentHandler(functor: StateChangedEvent)
//	{
//		this.onSent.push(functor);
//	}
//
//	public addOnFailureHandler(functor: StateChangedEvent)
//	{
//		this.onFailure.push(functor);
//	}
//
//	public request(params: any, async: boolean): void
//	{
//		let req = new XMLHttpRequest();
//		req.open(this.method, this.handler, async);
//
//		req.onreadystatechange = (state: Event) =>
//		{
//			let currentState = req.readyState;
//			let statusCode = req.status;
//
//			let callback = (value: StateChangedEvent, index: number, array) =>
//			{
//				value(currentState, statusCode);
//			};
//
//
//			if (req.readyState == RequestState.SENT)
//			{
//				this.onSent.forEach(callback);
//				return;
//			}
//
//			if (req.status != 200)
//			{
//				this.onFailure.forEach(callback);
//				return;
//			}
//
//			if (req.readyState == RequestState.READY)
//			{
//				this.onSuccess.forEach(callback);
//				return;
//			}
//		};
//
//		req.send(params);
//	}
//} 
//# sourceMappingURL=ajax.js.map