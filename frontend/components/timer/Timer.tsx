"use client"

import {useEffect, useRef, useState} from "react";

type toTimeFunction = (time: number) => string;

const TIME_MAP = {
    'FOCUS' : 25*60,
    'BREAK': 5*60,
}

const toPad : toTimeFunction = (time: number) => time.toString().padStart(2, "0");
const toMinute : toTimeFunction = (time) => toPad(Math.floor(time / 60));
const toSecond : toTimeFunction = (time) => toPad(time % 60);

const Timer = () => {
    const [times, setTimes] = useState<number>(25*60);
    const [isActive, setIsActive] = useState<boolean>(false);
    const timerInterval = useRef();
    const [session, setSession] = useState<'FOCUS' | 'BREAK'>('FOCUS');


    useEffect(()=>{
        if(isActive){
            timerInterval.current = setInterval(() => {
                setTimes((prev)=>{
                    if(prev <= 1){
                        setIsActive(false);
                        return 0;
                    }
                    return prev-1;
                });
            }, 1000)
        }else {
            if(timerInterval.current){
                clearInterval(timerInterval.current);
            }
        }
        return () => {
            if(timerInterval.current){
                clearInterval(timerInterval.current);
            }
        }
    }, [isActive]);

    useEffect(() => {
        setTimes(TIME_MAP[session]);
    }, [session]);

    const changeSession = (session : 'FOCUS' | 'BREAK') => {
        if(!isActive){
            setSession(session);
        }
    }

    const baseButtonClass = "px-2 py-2 cursor-pointer"
    const baseButtonColoredClass = `${baseButtonClass} bg-accent hover:bg-accent-hover text-main-text`

    return (
        <div className={"rounded-lg shadow-lg text-center max-w-sm mx-auto"}>
            <div>
                <button
                    className={`rounded-tl-lg w-1/2 py-1 ${session === 'FOCUS' ? 'bg-focus' : 'bg-session-inactive'} text-main-text cursor-pointer`}
                    onClick={() => changeSession('FOCUS')}
                >
                    FOCUS (25 min)
                </button>
                <button
                    className={`rounded-tr-lg w-1/2 py-1 ${session === 'BREAK' ? 'bg-break' : 'bg-session-inactive'} text-main-text cursor-pointer`}
                    onClick={() => changeSession('BREAK')}
                >
                    Short BREAK (5min)
                </button>
            </div>
            <div id="timer" className={"text-center"}>
                <p className={"text-8xl font-mono tracking-wide bg-back-dark text-main-text py-5"}>
                    <span>
                        {toMinute(times)}
                    </span>
                    <span className={"mx-2 animate-pulse"}>
                        :
                    </span>
                    <span>
                        {toSecond(times)}
                    </span>
                </p>
            </div>
            <div id="timer-button">
                {
                    isActive
                        ?
                        <div className={"w-full"}>
                            <button
                                onClick={() => setIsActive(false)}
                                className={`${baseButtonColoredClass} rounded-b-lg w-full`}
                            >
                                일시정지
                            </button>

                        </div>
                        :
                        <div className={"w-full h-full"}>
                            <button
                                className={`${baseButtonColoredClass} rounded-bl-lg w-1/3 h-full`}
                            >
                                초기화
                            </button>
                            <button
                                onClick={() => setIsActive(true)}
                                className={`${baseButtonColoredClass} w-1/3 h-full`}
                            >
                                시작
                            </button>
                            <button
                                className={`${baseButtonColoredClass} rounded-br-lg w-1/3 h-full`}
                            >
                                완료
                            </button>
                        </div>
                }
            </div>
        </div>
    )
}

export default Timer;