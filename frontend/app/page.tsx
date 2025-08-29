import Image from "next/image";
import Link from "next/link";

export default function Home() {
  return (
      <div>
        Main
          <Link
              href={'/timer'}
          >
              타이머
          </Link>
      </div>
  );
}
