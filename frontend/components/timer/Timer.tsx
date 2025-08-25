"use client"

import {useEffect, useRef, useState} from "react";

type toTimeFunction = (time: number) => string;

const toPad : toTimeFunction = (time: number) => time.toString().padStart(2, "0");

const toMinute : toTimeFunction = (time) => toPad(Math.floor(time / 60));

const toSecond : toTimeFunction = (time) => toPad(time % 60);

const Timer = () => {
    const [times, setTimes] = useState<number>(25*60);
    const [isActive, setIsActive] = useState<boolean>(false);
    const timerInterval = useRef();

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

    return (
        <div>
            <div id="timer">
                <p>
                    <span>
                        {toMinute(times)}
                    </span>
                    <span>:</span>
                    <span>
                        {toSecond(times)}
                    </span>
                </p>
            </div>
            <div id="timer-button">
                <button
                    onClick={() => setIsActive(true)}
                    className={"border-green-700 bg-green-300 px-2 py-1 rounded-md text-white cursor-pointer hover:bg-green-600"}
                >
                    시작
                </button>
                <button
                    onClick={() => setIsActive(false)}
                    className={"border-green-700 bg-green-300 px-2 py-1 rounded-md text-white cursor-pointer hover:bg-green-600"}
                >
                    일시정지
                </button>
            </div>
        </div>
    )
}

export default Timer;